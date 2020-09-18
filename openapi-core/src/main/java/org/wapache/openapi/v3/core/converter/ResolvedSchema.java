package org.wapache.openapi.v3.core.converter;

import org.wapache.openapi.v3.models.media.Schema;

import java.util.Map;

public class ResolvedSchema {
    public Schema schema;
    public Map<String, Schema> referencedSchemas;
}
