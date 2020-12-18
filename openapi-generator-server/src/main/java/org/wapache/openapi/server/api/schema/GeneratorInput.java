package org.wapache.openapi.server.api.schema;

import com.fasterxml.jackson.databind.JsonNode;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.parser.core.models.AuthorizationValue;

import java.util.Map;

@Schema(title = "代码生成配置选项", description = "")
public class GeneratorInput {

    @Schema(
        implementation = Object.class, // OpenAPI.class对象太大了, 会导致界面显示过多的属性。
        title = "OpenAPI对象",
        description = "根据这个OpenAPI对象来生成代码, OpenAPI对象的定义可以在OpenAPI规范中找到。" +
            "`spec`和`openAPIUrl`两者必须提供其中一个, 且仅当`openAPIUrl`没有提供时此属性才会生效。"
    )
    private JsonNode spec;

    @Schema(
        title = "OpenAPI文档的URL",
        description = "根据这个网址的OpenAPI文档的定义来生成代码, " +
            "`spec`和`openAPIUrl`两者必须提供其中一个。",
        example = "https://raw.githubusercontent.com/OpenAPITools/openapi-generator/master/modules/openapi-generator/src/test/resources/2_0/petstore.yaml"
    )
    private String openAPIUrl;

    @Schema(title = "选项名称和值", description = " 可以通过 `GET /gen/clients/{language}` 接口查询选项说明. ")
    private Map<String, String> options;

    @Schema(title = "认证信息", description = "")
    private AuthorizationValue authorizationValue;

    public AuthorizationValue getAuthorizationValue() {
        return authorizationValue;
    }

    public void setAuthorizationValue(AuthorizationValue authorizationValue) {
        this.authorizationValue = authorizationValue;
    }

    public JsonNode getSpec() {
        return spec;
    }

    public void setSpec(JsonNode spec) {
        this.spec = spec;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getOpenAPIUrl() {
        return openAPIUrl;
    }

    public void setOpenAPIUrl(String url) {
        this.openAPIUrl = url;
    }
}
