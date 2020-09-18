package org.wapache.openapi.v3.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.wapache.openapi.v3.models.responses.ApiResponses;
import org.wapache.openapi.v3.models.responses.ApiResponse;

import java.io.IOException;
import java.util.Map.Entry;

public class ApiResponsesSerializer extends JsonSerializer<ApiResponses> {

    @Override
    public void serialize(
            ApiResponses value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        if (value != null && value.getExtensions() != null && !value.getExtensions().isEmpty()) {
            jgen.writeStartObject();

            if (!value.isEmpty()) {
                for (Entry<String, ApiResponse> entry: value.entrySet()) {
                    jgen.writeObjectField(entry.getKey() , entry.getValue());
                }
            }
            for (Entry<String, Object> entry: value.getExtensions().entrySet()) {
                jgen.writeObjectField(entry.getKey(), entry.getValue());
            }
            jgen.writeEndObject();
        } else {
            provider.defaultSerializeValue(value, jgen);
        }
    }
}
