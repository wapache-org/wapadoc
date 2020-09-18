package org.wapache.openapi.v3.integration;

import org.wapache.openapi.v3.integration.api.OpenAPIConfiguration;
import org.wapache.openapi.v3.integration.api.OpenApiConfigurationLoader;

import java.io.IOException;

// TODO
public class URLOpenApiConfigurationLoader implements OpenApiConfigurationLoader {

    @Override
    public OpenAPIConfiguration load(String path) throws IOException {
        return null;
    }

    @Override
    public boolean exists(String path) {
        return false;
    }
}
