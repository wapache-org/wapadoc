package org.wapache.openapi.v3.parser.processors;

import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.examples.Example;
import org.wapache.openapi.v3.models.media.MediaType;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.parameters.RequestBody;
import org.wapache.openapi.v3.parser.ResolverCache;
import org.wapache.openapi.v3.parser.models.RefFormat;
import org.wapache.openapi.v3.parser.util.RefUtils;

import java.util.Map;

/**
 * Created by gracekarina on 20/06/17.
 */
public class RequestBodyProcessor {
    private final SchemaProcessor schemaProcessor;
    private final ExternalRefProcessor externalRefProcessor;
    private final ExampleProcessor exampleProcessor;
    private final ResolverCache cache;
    private final OpenAPI openAPI;

    public RequestBodyProcessor(ResolverCache cache, OpenAPI openAPI) {
        schemaProcessor = new SchemaProcessor(cache,openAPI);
        exampleProcessor = new ExampleProcessor(cache,openAPI);
        this.externalRefProcessor = new ExternalRefProcessor(cache, openAPI);
        this.cache = cache;
        this.openAPI = openAPI;
    }

    public void processRequestBody(RequestBody requestBody) {

        if (requestBody.get$ref() != null){
            processReferenceRequestBody(requestBody);
        }
        Schema schema = null;
        if(requestBody.getContent() != null){
            Map<String,MediaType> content = requestBody.getContent();
            for( String mediaName : content.keySet()) {
                MediaType mediaType = content.get(mediaName);
                if(mediaType.getSchema()!= null) {
                    schema = mediaType.getSchema();
                    if (schema != null) {
                        schemaProcessor.processSchema(schema);
                    }
                }
                if(mediaType.getExamples() != null) {
                    for(Example ex: mediaType.getExamples().values()){
                        exampleProcessor.processExample(ex);
                    }
                }
            }
        }
    }


    public void processReferenceRequestBody(RequestBody requestBody){
        RefFormat refFormat = RefUtils.computeRefFormat(requestBody.get$ref());
        String $ref = requestBody.get$ref();
        if (RefUtils.isAnExternalRefFormat(refFormat)){
            final String newRef = externalRefProcessor.processRefToExternalRequestBody($ref, refFormat);

            if (newRef != null) {
                requestBody.set$ref(newRef);
            }
        }
    }

}
