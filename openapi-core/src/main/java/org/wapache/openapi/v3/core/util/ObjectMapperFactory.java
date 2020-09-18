package org.wapache.openapi.v3.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.wapache.openapi.v3.core.jackson.SchemaSerializer;
import org.wapache.openapi.v3.core.jackson.mixin.ComponentsMixin;
import org.wapache.openapi.v3.core.jackson.mixin.DateSchemaMixin;
import org.wapache.openapi.v3.core.jackson.mixin.ExtensionsMixin;
import org.wapache.openapi.v3.core.jackson.mixin.OpenAPIMixin;
import org.wapache.openapi.v3.core.jackson.mixin.OperationMixin;
import org.wapache.openapi.v3.core.jackson.mixin.SchemaMixin;
import org.wapache.openapi.v3.models.Components;
import org.wapache.openapi.v3.models.ExternalDocumentation;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.Operation;
import org.wapache.openapi.v3.models.PathItem;
import org.wapache.openapi.v3.models.Paths;
import org.wapache.openapi.v3.models.callbacks.Callback;
import org.wapache.openapi.v3.models.examples.Example;
import org.wapache.openapi.v3.models.headers.Header;
import org.wapache.openapi.v3.models.info.Contact;
import org.wapache.openapi.v3.models.info.Info;
import org.wapache.openapi.v3.models.info.License;
import org.wapache.openapi.v3.models.links.Link;
import org.wapache.openapi.v3.models.links.LinkParameter;
import org.wapache.openapi.v3.models.media.DateSchema;import org.wapache.openapi.v3.models.media.Encoding;
import org.wapache.openapi.v3.models.media.EncodingProperty;
import org.wapache.openapi.v3.models.media.MediaType;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.media.XML;
import org.wapache.openapi.v3.models.parameters.Parameter;
import org.wapache.openapi.v3.models.parameters.RequestBody;
import org.wapache.openapi.v3.models.responses.ApiResponse;
import org.wapache.openapi.v3.models.responses.ApiResponses;
import org.wapache.openapi.v3.models.security.OAuthFlow;
import org.wapache.openapi.v3.models.security.OAuthFlows;
import org.wapache.openapi.v3.models.security.Scopes;
import org.wapache.openapi.v3.models.security.SecurityScheme;
import org.wapache.openapi.v3.models.servers.Server;
import org.wapache.openapi.v3.models.servers.ServerVariable;
import org.wapache.openapi.v3.models.servers.ServerVariables;
import org.wapache.openapi.v3.models.tags.Tag;

import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectMapperFactory {

    protected static ObjectMapper createJson() {
        return create(null);
    }

    protected static ObjectMapper createYaml() {
        YAMLFactory factory = new YAMLFactory();
        factory.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        factory.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
        factory.enable(YAMLGenerator.Feature.SPLIT_LINES);
        factory.enable(YAMLGenerator.Feature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS);

        return create(factory);
    }

    private static ObjectMapper create(JsonFactory jsonFactory) {
        ObjectMapper mapper = jsonFactory == null ? new ObjectMapper() : new ObjectMapper(jsonFactory);

        // handle ref schema serialization skipping all other props
        mapper.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @Override
                    public JsonSerializer<?> modifySerializer(
                            SerializationConfig config, BeanDescription desc, JsonSerializer<?> serializer) {
                        if (Schema.class.isAssignableFrom(desc.getBeanClass())) {
                            return new SchemaSerializer((JsonSerializer<Object>) serializer);
                        }
                        return serializer;
                    }
                });
            }
        });

        Module deserializerModule = new DeserializationModule();
        mapper.registerModule(deserializerModule);
        mapper.registerModule(new JavaTimeModule());

        Map<Class<?>, Class<?>> sourceMixins = new LinkedHashMap<>();

        sourceMixins.put(ApiResponses.class, ExtensionsMixin.class);
        sourceMixins.put(ApiResponse.class, ExtensionsMixin.class);
        sourceMixins.put(Callback.class, ExtensionsMixin.class);
        sourceMixins.put(Components.class, ComponentsMixin.class);
        sourceMixins.put(Contact.class, ExtensionsMixin.class);
        sourceMixins.put(Encoding.class, ExtensionsMixin.class);
        sourceMixins.put(EncodingProperty.class, ExtensionsMixin.class);
        sourceMixins.put(Example.class, ExtensionsMixin.class);
        sourceMixins.put(ExternalDocumentation.class, ExtensionsMixin.class);
        sourceMixins.put(Header.class, ExtensionsMixin.class);
        sourceMixins.put(Info.class, ExtensionsMixin.class);
        sourceMixins.put(License.class, ExtensionsMixin.class);
        sourceMixins.put(Link.class, ExtensionsMixin.class);
        sourceMixins.put(LinkParameter.class, ExtensionsMixin.class);
        sourceMixins.put(MediaType.class, ExtensionsMixin.class);
        sourceMixins.put(OAuthFlow.class, ExtensionsMixin.class);
        sourceMixins.put(OAuthFlows.class, ExtensionsMixin.class);
        sourceMixins.put(OpenAPI.class, OpenAPIMixin.class);
        sourceMixins.put(Operation.class, OperationMixin.class);
        sourceMixins.put(Parameter.class, ExtensionsMixin.class);
        sourceMixins.put(PathItem.class, ExtensionsMixin.class);
        sourceMixins.put(Paths.class, ExtensionsMixin.class);
        sourceMixins.put(RequestBody.class, ExtensionsMixin.class);
        sourceMixins.put(Scopes.class, ExtensionsMixin.class);
        sourceMixins.put(SecurityScheme.class, ExtensionsMixin.class);
        sourceMixins.put(Server.class, ExtensionsMixin.class);
        sourceMixins.put(ServerVariable.class, ExtensionsMixin.class);
        sourceMixins.put(ServerVariables.class, ExtensionsMixin.class);
        sourceMixins.put(Tag.class, ExtensionsMixin.class);
        sourceMixins.put(XML.class, ExtensionsMixin.class);
        sourceMixins.put(Schema.class, SchemaMixin.class);
        sourceMixins.put(DateSchema.class, DateSchemaMixin.class);

        mapper.setMixIns(sourceMixins);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper;
    }

    public static ObjectMapper buildStrictGenericObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        try {
            mapper.configure(DeserializationFeature.valueOf("FAIL_ON_TRAILING_TOKENS"), true);
        } catch (Throwable e) {
            // add only if supported by Jackson version 2.9+
        }
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

}
