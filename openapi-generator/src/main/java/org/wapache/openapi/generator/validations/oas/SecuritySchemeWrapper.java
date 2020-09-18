package org.wapache.openapi.generator.validations.oas;

import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.security.SecurityScheme;

/**
 * Encapsulates an OAS parameter.
 */
public class SecuritySchemeWrapper {
    OpenAPI specification;
    private SecurityScheme securityScheme;

    /**
     * Constructs a new instance of {@link SecuritySchemeWrapper}
     *
     * @param specification The OAS specification
     * @param securityScheme The OAS securityScheme
     */
    SecuritySchemeWrapper(OpenAPI specification, SecurityScheme securityScheme) {
        this.specification = specification;
        this.securityScheme = securityScheme;
    }

    /**
     * Return the OAS securityScheme
     *
     * @return the OAS securityScheme
     */
    public SecurityScheme getSecurityScheme() {
        return securityScheme;
    }

    /**
     * Returns the OpenAPI specification.
     *
     * @return The the OpenAPI specification.
     */
    public OpenAPI getOpenAPI() {
        return specification;
    }
}
