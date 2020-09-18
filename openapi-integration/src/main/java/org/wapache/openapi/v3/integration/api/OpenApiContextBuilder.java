package org.wapache.openapi.v3.integration.api;

import org.wapache.openapi.v3.integration.OpenApiConfigurationException;

public interface OpenApiContextBuilder {

    OpenApiContext buildContext(boolean init) throws OpenApiConfigurationException;

}
