package test.org.springdoc.api.app127;

import org.wapache.openapi.v3.annotations.media.Schema;

public class Umbrella {

  @Schema(description = "This reference to abstract class causes weird YAML tag to be added", anyOf = ConcreteObjectA.class)
  private final AbstractObject object;

  public Umbrella(AbstractObject object) {
    this.object = object;
  }

  public AbstractObject getObject() {
    return object;
  }
}
