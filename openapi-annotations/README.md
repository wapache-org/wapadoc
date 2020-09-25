
写在配置上

```shell script

@OpenAPIDefinition(

    info = @Info(
    ),
    tags = {
      @Tag()
    },
    security = {
        @SecurityRequirement(name = "petstore_auth", scopes = { "write:pets", "read:pets" })
    }

)

```




写在类上

```shell script

@Hidden()

@Tag(name = "pet", description = "宠物接口")


````

写在方法上

```shell script

@Hidden()

@Operation(
    tags = { "pet" },
    operationId = "",
    summary = "添加一只宠物到宠物商店",
    description = "添加一只宠物到宠物商店",

    parameters({
        @Parameter(description = "宠物信息", required = true),
        @Parameter(description = "宠物信息", required = true)
    }),
    requestBody = @ApiRequestBody(),
    responses = ({
        @ApiResponse(responseCode = "200", description = "操作成功", content = {
            @Content(mediaType = "application/xml", schema = @Schema(implementation = Pet.class)),
            @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class))
        }),
        @ApiResponse(responseCode = "405", description = "非法的输入")
    })

    security = {
        @SecurityRequirement(name = "petstore_auth", scopes = { "write:pets", "read:pets" })
    }
)

```

写在方法参数上

```shell script

@Parameter(description = "宠物信息", required = true)

```

写在ApiModel类上

```shell script


```


写在ApiModel类的属性上


```shell script

	@Schema(example = "10", description = "")

```
















