package org.wapache.openapi.v3.core.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.wapache.openapi.v3.core.jackson.ApiResponsesSerializer;
import org.wapache.openapi.v3.core.jackson.CallbackSerializer;
import org.wapache.openapi.v3.models.callbacks.Callback;
import org.wapache.openapi.v3.models.responses.ApiResponses;

import java.util.Map;

public abstract class OperationMixin {

    @JsonAnyGetter
    public abstract Map<String, Object> getExtensions();

    @JsonAnySetter
    public abstract void addExtension(String name, Object value);

    @JsonSerialize(contentUsing = CallbackSerializer.class)
    public abstract Map<String, Callback> getCallbacks();

    @JsonSerialize(using = ApiResponsesSerializer.class)
    public abstract ApiResponses getResponses();

}
