package org.wapache.openapi.v3.core.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.wapache.openapi.v3.core.jackson.PathsSerializer;
import org.wapache.openapi.v3.models.Paths;
import java.util.Map;

public abstract class OpenAPIMixin {

    @JsonAnyGetter
    public abstract Map<String, Object> getExtensions();

    @JsonAnySetter
    public abstract void addExtension(String name, Object value);

    @JsonSerialize(using = PathsSerializer.class)
    public abstract Paths getPaths();
}
