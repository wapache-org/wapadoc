package org.wapache.openapi.v3.parser.processors;

import org.wapache.openapi.v3.models.examples.Example;
import org.wapache.openapi.v3.models.headers.Header;
import org.wapache.openapi.v3.models.links.Link;
import org.wapache.openapi.v3.models.media.MediaType;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.responses.ApiResponse;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.parser.ResolverCache;
import org.wapache.openapi.v3.parser.models.RefFormat;
import org.wapache.openapi.v3.parser.util.RefUtils;

import java.util.Map;

public class ResponseProcessor {

    private final SchemaProcessor schemaProcessor;
    private final HeaderProcessor headerProcessor;
    private final LinkProcessor linkProcessor;
    private final ExternalRefProcessor externalRefProcessor;
    private final ExampleProcessor exampleProcessor;
    private final ResolverCache cache;
    private final OpenAPI openAPI;

    public ResponseProcessor(ResolverCache cache, OpenAPI openAPI) {
        schemaProcessor = new SchemaProcessor(cache,openAPI);
        headerProcessor = new HeaderProcessor(cache,openAPI);
        linkProcessor = new LinkProcessor(cache,openAPI);
        exampleProcessor = new ExampleProcessor(cache,openAPI);
        this.externalRefProcessor = new ExternalRefProcessor(cache, openAPI);
        this.cache = cache;
        this.openAPI = openAPI;
    }

    public void processResponse(ApiResponse response) {

        if (response.get$ref() != null){
            processReferenceResponse(response);
        }

        Schema schema = null;
        if(response.getContent() != null){
            Map<String,MediaType> content = response.getContent();
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
        if (response.getHeaders() != null){
            Map<String,Header> headers = response.getHeaders();
            for(String headerName : headers.keySet()){
                Header header = headers.get(headerName);
                headerProcessor.processHeader(header);
            }


        }
        if (response.getLinks() != null){
            Map<String,Link> links = response.getLinks();
            for(String linkName : links.keySet()){
                Link link = links.get(linkName);
                linkProcessor.processLink(link);
            }
        }
    }

    public void processReferenceResponse(ApiResponse response){
        RefFormat refFormat = RefUtils.computeRefFormat(response.get$ref());
        String $ref = response.get$ref();
        if (RefUtils.isAnExternalRefFormat(refFormat)){
            externalRefProcessor.processRefToExternalResponse($ref, refFormat);
        }
    }
}