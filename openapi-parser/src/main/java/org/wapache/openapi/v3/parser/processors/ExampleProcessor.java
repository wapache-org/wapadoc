package org.wapache.openapi.v3.parser.processors;

import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.examples.Example;
import org.wapache.openapi.v3.parser.ResolverCache;
import org.wapache.openapi.v3.parser.models.RefFormat;
import org.wapache.openapi.v3.parser.util.RefUtils;

import java.util.List;

/**
 * Created by gracekarina on 23/06/17.
 */
public class ExampleProcessor {
    private final ResolverCache cache;
    private final OpenAPI openAPI;
    private final ExternalRefProcessor externalRefProcessor;

    public ExampleProcessor(ResolverCache cache, OpenAPI openAPI) {
        this.cache = cache;
        this.openAPI = openAPI;
        this.externalRefProcessor = new ExternalRefProcessor(cache, openAPI);
    }

    public void processExample(Example example) {

        if (example.get$ref() != null){
           processReferenceExample(example);
        }
    }

    public void processExample(List<Example> examples) {
        for(Example example: examples){
            if (example.get$ref() != null){
                processReferenceExample(example);
            }
        }
    }
    private void processReferenceExample(Example example){
        RefFormat refFormat = RefUtils.computeRefFormat(example.get$ref());
        String $ref = example.get$ref();
        if (RefUtils.isAnExternalRefFormat(refFormat)){
            final String newRef = externalRefProcessor.processRefToExternalExample($ref, refFormat);
            if (newRef != null) {
                example.set$ref(newRef);
            }
        }
    }
}
