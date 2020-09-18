package org.wapache.openapi.v3.integration;

import org.wapache.openapi.v3.integration.api.OpenAPIConfigBuilder;
import org.wapache.openapi.v3.integration.api.OpenAPIConfiguration;
import org.wapache.openapi.v3.integration.api.OpenApiConfigurationLoader;

import java.io.IOException;
import java.util.ServiceLoader;

// doesn't support multiple configs
public class ServiceOpenApiConfigurationLoader implements OpenApiConfigurationLoader {

    @Override
    public OpenAPIConfiguration load(String path) throws IOException {

        ServiceLoader<OpenAPIConfigBuilder> loader = ServiceLoader.load(OpenAPIConfigBuilder.class);
        if (loader.iterator().hasNext()) {
            return loader.iterator().next().build();
        }
        throw new IOException("Error loading OpenAPIConfigBuilder service implementation.");
    }

    @Override
    public boolean exists(String path) {

        try {
            ServiceLoader<OpenAPIConfigBuilder> loader = ServiceLoader.load(OpenAPIConfigBuilder.class);
            if (loader.iterator().hasNext()) {
                loader.iterator().next();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
