package org.wapache.openapi.v3.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.wapache.openapi.v3.core.util.ObjectMapperFactory;

public class IntegrationObjectMapperFactory extends ObjectMapperFactory {

    public static ObjectMapper createJson() {
        return ObjectMapperFactory.createJson();
    }
}
