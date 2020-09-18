package org.wapache.openapi.v3.core.util;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.wapache.openapi.v3.models.Paths;
import org.wapache.openapi.v3.models.callbacks.Callback;
import org.wapache.openapi.v3.models.headers.Header;
import org.wapache.openapi.v3.models.media.Encoding;
import org.wapache.openapi.v3.models.media.EncodingProperty;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.parameters.Parameter;
import org.wapache.openapi.v3.models.responses.ApiResponses;
import org.wapache.openapi.v3.models.security.SecurityScheme;

public class DeserializationModule extends SimpleModule {

    public DeserializationModule() {

        this.addDeserializer(Schema.class, new ModelDeserializer());
        this.addDeserializer(Parameter.class, new ParameterDeserializer());
        this.addDeserializer(Header.StyleEnum.class, new HeaderStyleEnumDeserializer());
        this.addDeserializer(Encoding.StyleEnum.class, new EncodingStyleEnumDeserializer());
        this.addDeserializer(EncodingProperty.StyleEnum.class, new EncodingPropertyStyleEnumDeserializer());

        this.addDeserializer(SecurityScheme.class, new SecuritySchemeDeserializer());

        this.addDeserializer(ApiResponses.class, new ApiResponsesDeserializer());
        this.addDeserializer(Paths.class, new PathsDeserializer());
        this.addDeserializer(Callback.class, new CallbackDeserializer());
    }
}
