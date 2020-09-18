package org.wapache.openapi.generator.validations.oas;

import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.v3.models.OpenAPI;

/**
 * Encapsulates an OAS schema.
 */
public class SchemaWrapper {
    OpenAPI specification;
    private Schema schema;

    /**
     * Constructs a new instance of {@link SchemaWrapper}
     *
     * @param specification The OAS specification
     * @param schema The OAS schema
     */
    SchemaWrapper(OpenAPI specification, Schema schema) {
        this.specification = specification;
        this.schema = schema;
    }

    /**
     * Return the OAS schema
     *
     * @return the OAS schema
     */
    public Schema getSchema() {
        return schema;
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
