package org.wapache.openapi.v3.parser.processors;


import org.wapache.openapi.v3.models.examples.Example;
import org.wapache.openapi.v3.models.media.MediaType;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.parameters.Parameter;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.parser.ResolverCache;
import org.wapache.openapi.v3.parser.models.RefFormat;
import org.wapache.openapi.v3.parser.util.RefUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ParameterProcessor {

    private final ResolverCache cache;
    private final SchemaProcessor schemaProcessor;
    private final ExampleProcessor exampleProcessor;
    private final OpenAPI openAPI;
    private final ExternalRefProcessor externalRefProcessor;


    public ParameterProcessor(ResolverCache cache, OpenAPI openAPI) {
        this.cache = cache;
        this.openAPI = openAPI;
        this.schemaProcessor = new SchemaProcessor(cache,openAPI);
        this.exampleProcessor = new ExampleProcessor(cache,openAPI);
        this.externalRefProcessor = new ExternalRefProcessor(cache, openAPI);
    }

    public void processParameter(Parameter parameter) {
        String $ref = parameter.get$ref();
        if($ref != null){
            RefFormat refFormat = RefUtils.computeRefFormat(parameter.get$ref());
            if (RefUtils.isAnExternalRefFormat(refFormat)){
                final String newRef = externalRefProcessor.processRefToExternalParameter($ref, refFormat);
                if (newRef != null) {
                    parameter.set$ref(newRef);
                }
            }
        }
        if (parameter.getSchema() != null){
            schemaProcessor.processSchema(parameter.getSchema());
        }
        if (parameter.getExamples() != null){
            Map <String, Example> examples = parameter.getExamples();
            for(String exampleName: examples.keySet()){
                final Example example = examples.get(exampleName);
                exampleProcessor.processExample(example);
            }
        }
        Schema schema = null;
        if(parameter.getContent() != null) {
            Map<String,MediaType> content = parameter.getContent();
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

   public List<Parameter> processParameters(List<Parameter> parameters) {

        if (parameters == null) {
            return null;
        }

        final List<Parameter> processedPathLevelParameters = new ArrayList<>();
        final List<Parameter> refParameters = new ArrayList<>();

        for (Parameter parameter : parameters) {
            if (parameter.get$ref() != null) {
                RefFormat refFormat = RefUtils.computeRefFormat(parameter.get$ref());
                final Parameter resolvedParameter = cache.loadRef(parameter.get$ref(), refFormat, Parameter.class);
                if (parameter.get$ref().startsWith("#") && parameter.get$ref().indexOf("#/components/parameters") <= -1) {
                    //TODO: Not possible to add warning during resolve doesn't accept result as an input. Hence commented below line.
                    //result.warning(location, "The parameter should use Reference Object to link to parameters that are defined at the OpenAPI Object's components/parameters.");
                    continue;
                }

                if(resolvedParameter == null) {
                    // can't resolve it!
                    processedPathLevelParameters.add(parameter);
                    continue;
                }
                // if the parameter exists, replace it
                boolean matched = false;
                for(Parameter param : processedPathLevelParameters) {
                    if(param != null && param.getName() != null && param.getName().equals(resolvedParameter.getName())) {
                        // ref param wins
                        matched = true;
                        break;
                    }
                }
                for(Parameter param : parameters) {
                    if(param.getName() != null) {
                        if (param.getName().equals(resolvedParameter.getName())) {
                            // ref param wins
                            matched = true;
                            break;
                        }
                    }
                }

                if(matched) {
                    refParameters.add(resolvedParameter);
                }
                else {
                    processedPathLevelParameters.add(resolvedParameter);
                }
            }
            else {
                processedPathLevelParameters.add(parameter);
            }
        }

        for(Parameter resolvedParameter : refParameters) {
            int pos = 0;
            for(Parameter param : processedPathLevelParameters) {
                if(param.getName().equals(resolvedParameter.getName())) {
                    // ref param wins
                    processedPathLevelParameters.set(pos, resolvedParameter);
                    break;
                }
                pos++;
            }

        }

        for (Parameter parameter : processedPathLevelParameters) {
            Schema schema = parameter.getSchema();
            if(schema != null){
                schemaProcessor.processSchema(schema);
            }else if(parameter.getContent() != null){
                Map<String,MediaType> content = parameter.getContent();
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
            else if(parameter.getContent() != null){
                Map<String,MediaType> content = parameter.getContent();
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

        return processedPathLevelParameters;
    }
}
