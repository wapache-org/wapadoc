package test.org.springdoc.api.app91;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;
import org.wapache.openapi.v3.annotations.responses.ApiResponses;
import org.wapache.openapi.v3.annotations.tags.Tag;
import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Tag(name = "Demo", description = "The Demo API")
public class GreetingController {

	@GetMapping(value="/", produces = APPLICATION_JSON_VALUE)
	@Operation(summary = "This API will return a random greeting.")
	public ResponseEntity<Greeting> sayHello() {
		return ResponseEntity.ok(new Greeting(RandomStringUtils.randomAlphanumeric(10)));
	}

	@GetMapping("/test")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "item created"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid"),
			@ApiResponse(responseCode = "409", description = "an existing item already exists") })
	public ResponseEntity<Greeting> sayHello2() {
		return ResponseEntity.ok(new Greeting(RandomStringUtils.randomAlphanumeric(10)));
	}

}
