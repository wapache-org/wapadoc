package org.wapache.openapi.v3.core.filter;

import org.wapache.openapi.v3.core.model.ApiDescription;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.Operation;
import org.wapache.openapi.v3.models.PathItem;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.parameters.Parameter;
import org.wapache.openapi.v3.models.parameters.RequestBody;
import org.wapache.openapi.v3.models.responses.ApiResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OpenAPISpecFilter {

    Optional<OpenAPI> filterOpenAPI(
            OpenAPI openAPI,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<PathItem> filterPathItem(
            PathItem pathItem,
            ApiDescription api,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<Operation> filterOperation(
            Operation operation,
            ApiDescription api,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<Parameter> filterParameter(
            Parameter parameter,
            Operation operation,
            ApiDescription api,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<RequestBody> filterRequestBody(
            RequestBody requestBody,
            Operation operation,
            ApiDescription api,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<ApiResponse> filterResponse(
            ApiResponse response,
            Operation operation,
            ApiDescription api,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<Schema> filterSchema(
            Schema schema,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    Optional<Schema> filterSchemaProperty(
            Schema property,
            Schema schema,
            String propName,
            Map<String, List<String>> params,
            Map<String, String> cookies,
            Map<String, List<String>> headers);

    boolean isRemovingUnreferencedDefinitions();
}