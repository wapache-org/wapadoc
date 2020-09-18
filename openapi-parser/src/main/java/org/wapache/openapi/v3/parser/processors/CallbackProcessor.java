package org.wapache.openapi.v3.parser.processors;

import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.Operation;
import org.wapache.openapi.v3.models.PathItem;
import org.wapache.openapi.v3.models.callbacks.Callback;
import org.wapache.openapi.v3.models.parameters.Parameter;
import org.wapache.openapi.v3.parser.ResolverCache;
import org.wapache.openapi.v3.parser.models.RefFormat;
import org.wapache.openapi.v3.parser.util.RefUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by gracekarina on 23/06/17.
 */
public class CallbackProcessor {
    private final ResolverCache cache;
    private final OperationProcessor operationProcessor;
    private final ParameterProcessor parameterProcessor;
    private final OpenAPI openAPI;
    private final ExternalRefProcessor externalRefProcessor;

    public CallbackProcessor(ResolverCache cache, OpenAPI openAPI) {
        this.cache = cache;
        this.operationProcessor = new OperationProcessor(cache, openAPI);
        this.parameterProcessor = new ParameterProcessor(cache,openAPI);
        this.externalRefProcessor = new ExternalRefProcessor(cache, openAPI);
        this.openAPI = openAPI;
    }

    public void processCallback(Callback callback) {
        if (callback.get$ref() != null){
            processReferenceCallback(callback);
        }
        //Resolver PathItem
        for(String name : callback.keySet()){
            PathItem pathItem = callback.get(name);
            final Map<PathItem.HttpMethod, Operation> operationMap = pathItem.readOperationsMap();

            for (PathItem.HttpMethod httpMethod : operationMap.keySet()) {
                Operation operation = operationMap.get(httpMethod);
                operationProcessor.processOperation(operation);
            }

            List<Parameter> parameters = pathItem.getParameters();
            if (parameters != null) {
                for (Parameter parameter: parameters){
                    parameterProcessor.processParameter(parameter);
                }
            }
        }
    }

    public void processReferenceCallback(Callback callback ){
        String $ref = callback.get$ref();
        RefFormat refFormat = RefUtils.computeRefFormat($ref);
        if (RefUtils.isAnExternalRefFormat(refFormat)){
            final String newRef = externalRefProcessor.processRefToExternalCallback($ref, refFormat);
            if (newRef != null) {
                callback.set$ref("#/components/callbacks/"+newRef);
            }
        }
    }
}
