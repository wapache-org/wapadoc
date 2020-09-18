package test.org.springdoc.api.app116;

import org.wapache.openapi.v3.annotations.OpenAPIDefinition;
import org.wapache.openapi.v3.annotations.info.Info;
import org.wapache.openapi.v3.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@OpenAPIDefinition(info = @Info(title = "API Examples", version = "1.0"), tags = @Tag(name = "Operations"))
public class HelloController {

	@PostMapping("/foo")
	public String create(@RequestBody String foo) {
		return "foo";
	}
}


