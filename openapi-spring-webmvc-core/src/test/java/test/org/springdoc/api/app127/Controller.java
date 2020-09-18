package test.org.springdoc.api.app127;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.ExampleObject;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @Operation(summary = "Test Bug", responses = {
      @ApiResponse(responseCode = "200", description = "OK",
          content = @Content(
              schema = @Schema(implementation = Umbrella.class),
              examples = @ExampleObject(ref = "#/components/examples/umbrellaExample", name = "Example with weird YAML tag")
          )
      )
  })
  @GetMapping(value = "/bug", produces = MediaType.APPLICATION_JSON_VALUE)
  public Umbrella bug() {
    return new Umbrella(new ConcreteObjectA("a", "b"));
  }
}
