package org.wapache.openapi.v3.parser.processors;


import org.wapache.openapi.v3.models.examples.Example;
import org.wapache.openapi.v3.models.headers.Header;
import org.wapache.openapi.v3.models.media.MediaType;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.parser.ResolverCache;
import org.wapache.openapi.v3.parser.models.RefFormat;
import org.wapache.openapi.v3.parser.util.RefUtils;

import java.util.Map;


public class HeaderProcessor {

    private final ResolverCache cache;
    private final SchemaProcessor schemaProcessor;
    private final ExampleProcessor exampleProcessor;
    private final ExternalRefProcessor externalRefProcessor;
    private final OpenAPI openAPI;


    public HeaderProcessor(ResolverCache cache, OpenAPI openAPI) {
        this.cache = cache;
        this.openAPI = openAPI;
        this.schemaProcessor = new SchemaProcessor(cache,openAPI);
        this.exampleProcessor = new ExampleProcessor(cache,openAPI);
        this.externalRefProcessor = new ExternalRefProcessor(cache, openAPI);
    }

    public void processHeader(Header header) {

        if(header.get$ref() != null){
            RefFormat refFormat = RefUtils.computeRefFormat(header.get$ref());
            String $ref = header.get$ref();
            if (RefUtils.isAnExternalRefFormat(refFormat)){
                final String newRef = externalRefProcessor.processRefToExternalHeader($ref, refFormat);
                if (newRef != null) {
                    header.set$ref(newRef);
                }
            }
        }
        if (header.getSchema() != null) {
            schemaProcessor.processSchema(header.getSchema());

        }
        if (header.getExamples() != null){
            if (header.getExamples() != null) {
                Map<String,Example> examples = header.getExamples();
                for (String key : examples.keySet()){
                    exampleProcessor.processExample(header.getExamples().get(key));
                }

            }
        }
        Schema schema = null;
        if(header.getContent() != null) {
            Map<String,MediaType> content = header.getContent();
            for( String mediaName : content.keySet()) {
                MediaType mediaType = content.get(mediaName);
                if(mediaType.getSchema()!= null) {
                    schema = mediaType.getSchema();
                    if (schema != null) {
                        schemaProcessor.processSchema(schema);
                    }
                }
            }
        }
    }
}