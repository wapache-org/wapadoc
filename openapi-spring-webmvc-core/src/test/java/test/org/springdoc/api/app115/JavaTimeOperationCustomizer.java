package test.org.springdoc.api.app115;

import java.time.Duration;
import java.util.Map;

import org.wapache.openapi.v3.models.Operation;
import org.wapache.openapi.v3.models.media.Content;
import org.wapache.openapi.v3.models.media.Schema;
import org.wapache.openapi.spring.core.customizers.OperationCustomizer;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.wapache.openapi.v3.models.responses.ApiResponse;

@Component
public class JavaTimeOperationCustomizer implements OperationCustomizer {
	@Override
	public Operation customize(Operation operation, HandlerMethod handlerMethod) {
		if (handlerMethod.getReturnType().getParameterType().isAssignableFrom(Duration.class)) {
			for (Map.Entry<String, ApiResponse> entry:  operation.getResponses().entrySet()) {
				ApiResponse response = entry.getValue();
				Content content = response.getContent();
				if (content.containsKey(MediaType.APPLICATION_JSON_VALUE)) {
					Schema schema = content.get(MediaType.APPLICATION_JSON_VALUE).getSchema();
					schema.getProperties().clear();
					schema.setType("string");
				}
			}
		}
		return operation;
	}
}