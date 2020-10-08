package test.org.springdoc.api.app106;

import java.time.Instant;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.enums.ParameterIn;
import org.wapache.openapi.v3.annotations.media.Schema;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Operation(summary = "find-articles")
	@GetMapping("/")
	@Parameter(name = HttpHeaders.IF_MODIFIED_SINCE,
			description = "DateTime",
			in = ParameterIn.HEADER,
			schema = @Schema(type = "string", format = "date-time"),
			example = "2020-01-01T00:00:00.000Z"
	)
	public ResponseEntity<String> findArticles(
		@RequestHeader(value = HttpHeaders.IF_MODIFIED_SINCE, required = false)
		Instant modifiedSince
	) {
		return null;
	}

}
