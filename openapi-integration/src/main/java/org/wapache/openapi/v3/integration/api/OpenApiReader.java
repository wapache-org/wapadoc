package org.wapache.openapi.v3.integration.api;

import org.wapache.openapi.v3.models.OpenAPI;

import java.util.Map;
import java.util.Set;

public interface OpenApiReader {

    void setConfiguration(OpenAPIConfiguration openApiConfiguration);

    OpenAPI read(Set<Class<?>> classes, Map<String, Object> resources);

}
