package org.wapache.openapi.server.api.schema;

import org.wapache.openapi.v3.annotations.media.Schema;

@Schema(title = "代码生成结果", description = "")
public class ResponseCode {

    @Schema(
        title = "文件ID",
        description = "可以根据此编码调用`GET /gen/download/{fileId}`接口下载生成的代码。",
        example = "d40029be-eda6-4d62-b1ef-d05e2e91a72a"
    )
    private String code;

    @Schema(
        title = "文件下载地址",
        description =
            "是根据`GET /gen/download/{fileId}`接口拼接好的URL。" +
            "注意, 一个`文件ID`只能下载一次, 下载完成后文件就会被删除。",
        example = "http://localhost:8080/api/gen/download/d40029be-eda6-4d62-b1ef-d05e2e91a72a"
    )
    private String link;

    public ResponseCode() {}

    public ResponseCode(String code, String link) {
        setCode(code);
        setLink(link);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
