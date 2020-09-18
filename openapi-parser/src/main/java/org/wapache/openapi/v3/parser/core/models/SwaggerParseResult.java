package org.wapache.openapi.v3.parser.core.models;

import org.wapache.openapi.v3.models.OpenAPI;

import java.util.Collections;
import java.util.List;

public class SwaggerParseResult {
    private List<String> messages = null;
    private OpenAPI openAPI;

    public SwaggerParseResult messages(List<String> messages) {
        this.messages = messages;
        return this;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public OpenAPI getOpenAPI() {
        return openAPI;
    }

    public void setOpenAPI(OpenAPI openAPI) {
        this.openAPI = openAPI;
    }

    public static SwaggerParseResult ofError(String message){
        final SwaggerParseResult result = new SwaggerParseResult();
        result.setMessages(Collections.singletonList(message));
        return result;
    }
}
