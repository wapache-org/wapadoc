
从`springfox`迁移到`springdoc`: https://springdoc.org/migrating-from-springfox.html

用`org.wapache.openapi.v3.annotations`下的注解替换原来的注解:

    V2                                          -> V3
    @Api                                        -> @Tag
    @ApiIgnore                                  -> @Parameter(hidden = true) or @Operation(hidden = true) or @Hidden
    @ApiImplicitParam                           -> @Parameter
    @ApiImplicitParams                          -> @Parameters
    @ApiModel                                   -> @Schema
    @ApiModelProperty(hidden = true)            -> @Schema(accessMode = READ_ONLY)
    @ApiModelProperty                           -> @Schema
    @ApiOperation(value = "foo", notes = "bar") -> @Operation(summary = "foo", description = "bar")
    @ApiParam                                   -> @Parameter
    @ApiResponse(code = 404, message = "foo")   -> @ApiResponse(responseCode = "404", description = "foo")

`Docket` 换成`GroupedOpenApi`

配置文件里配置springdoc, 完整配置项在[这里](https://springdoc.org/springdoc-properties.html), 
最后代码里new一个`OpenAPI`

```java

class A {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SpringShop API")
                .description("Spring shop sample application")
                .version("v0.0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                .description("SpringShop Wiki Documentation")
                .url("https://springshop.wiki.github.org/docs"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .setGroup("springshop-public")
                .pathsToMatch("/public/**")
                .build();
    }
  
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .setGroup("springshop-admin")
                .pathsToMatch("/admin/**")
                .build();
    }

}
```

