package test.org.springdoc.api.app99;

import org.wapache.openapi.v3.annotations.responses.ApiResponse;
import org.wapache.openapi.v3.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class HelloController {

	@GetMapping
	@ApiResponses({
			@ApiResponse(responseCode = "202", description = "${test.app99.operation.persons.response.202.description}")
	})
	public void persons() {

	}

}
