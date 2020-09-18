package org.wapache.openapi.v3.integration;

import org.wapache.openapi.v3.core.util.Json;
import org.wapache.openapi.v3.core.util.Yaml;
import org.wapache.openapi.v3.integration.api.OpenAPIConfiguration;
import org.wapache.openapi.v3.integration.api.OpenApiConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface StringOpenApiConfigurationLoader extends OpenApiConfigurationLoader {

    Logger LOGGER = LoggerFactory.getLogger(StringOpenApiConfigurationLoader.class);

    default OpenAPIConfiguration deserializeConfig(String path, String configAsString) {

        try {
            if (path.toLowerCase().endsWith("json")) {
                return Json.mapper().readValue(configAsString, SwaggerConfiguration.class);
            } else { // assume yaml
                return Yaml.mapper().readValue(configAsString, SwaggerConfiguration.class);
            }

        } catch (Exception e) {
            LOGGER.error("exception reading config: " + e.getMessage(), e);
            return null;
        }

    }

}
