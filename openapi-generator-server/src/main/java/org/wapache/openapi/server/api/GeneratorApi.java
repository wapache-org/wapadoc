package org.wapache.openapi.server.api;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.wapache.openapi.generator.CliOption;
import org.wapache.openapi.server.api.schema.GeneratorInput;
import org.wapache.openapi.server.api.schema.ResponseCode;
import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.media.ArraySchema;
import org.wapache.openapi.v3.annotations.media.Content;
import org.wapache.openapi.v3.annotations.media.Schema;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;
import org.wapache.openapi.v3.annotations.responses.ApiResponses;
import org.wapache.openapi.v3.annotations.tags.Tag;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Tag(name = "gen", description = "OpenAPI代码生成服务")
public interface GeneratorApi {

    // ////////////////////////////////////////////////////////////////////////
    // region 客户端代码生成相关接口

    @RequestMapping(value = "/gen/clients", method = RequestMethod.GET)
    @Operation(
        operationId = "clientOptions",
        summary = "获取客户端开发语言列表",
        description = "获取客户端代码生成支持的所有开发语言",
        tags={ "clients", },
        responses = @ApiResponse(
            responseCode = "200",
            description = "操作成功",
            content = @Content(array = @ArraySchema(
                arraySchema = @Schema(title = "开发语言名称列表"),
                schema = @Schema(title = "开发语言名称")
            ))
        )
    )
    List<String> clientOptions();

    // TODO 这个路径和操作名起得不好, 改为/gen/clients/{language}/options
    @RequestMapping(
        value = "/gen/clients/{language}",
        produces = { "application/json" },
        method = RequestMethod.GET
    )
    @Operation(
        operationId = "getClientOptions",
        summary = "获取客户端代码生成的配置选项",
        description = "",
        tags={ "clients", },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "操作成功",
                content = {
                    @Content(
                        schema = @Schema(
                            ref = "#/components/schemas/MapOfOptionNameAndCliOption"
                        )
//                        schema = @Schema(type = "object", title = "配置选项键值对", description = "key是配置项名称, value是配置项对象")
                    )
                }
            )
        }
    )
    // TODO 暂时还没有找到可以直接生成Map<String, CliOption>的配置方法, Schema注解没有additionalProperties
//    @Schema(title = "配置选项键值对", ref = "#/components/schemas/MapOfOptionNameAndCliOption")
    ResponseEntity<Map<String, CliOption>> getClientOptions(
        @PathVariable
        @Schema(
            title = "开发语言",
            description = "客户端代码使用的开发语言"
        )
        String language
    );

    @RequestMapping(
        method = RequestMethod.POST,
        value = "/gen/clients/{language}"
    )
    @Operation(
        operationId = "generateClient",
        summary = "生成客户端代码",
        description = "根据`OpenAPI文档`和`代码生成配置选项`生成客户端代码。" +
            "可以根据返回的`code`调用`GET /gen/download/{fileId}`接口下载生成的客户端代码。",
        tags={ "clients", }
    )
    ResponseEntity<ResponseCode> generateClient(
        @PathVariable
        @Schema(
            title = "开发语言",
            description = "客户端代码使用的开发语言"
        )
        String language,

        @Valid
        @RequestBody
        @Parameter(required=true)
        GeneratorInput generatorInput
    );

    // endregion 客户端代码生成相关接口
    // ////////////////////////////////////////////////////////////////////////
    // region 服务端代码生成相关接口

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/gen/servers"
    )
    @Operation(
        summary = "获取服务端开发语言列表",
        description = "获取客户端代码生成支持的所有开发语言",
        operationId = "serverOptions",
        tags={ "servers", },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "操作成功",
                content = {
                    @Content(array = @ArraySchema(schema = @Schema(title = "服务端开发语言列表", description = "")))
                }
            )
        }
    )
    List<String> serverOptions();

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/gen/servers/{framework}",
        produces = { "application/json" }
    )
    @Operation(
        operationId = "getServerOptions",
        summary = "获取服务端开发框架代码生成的配置选项",
        description = "",
        tags={ "servers", },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "操作成功",
                content = {
                    @Content(
                        schema = @Schema(
                            ref = "#/components/schemas/MapOfOptionNameAndCliOption"
                        )
                    )
                }
            )
        }
    )
    ResponseEntity<Map<String, CliOption>> getServerOptions(
        @Parameter(description = "服务端开发框架",required=true)
        @PathVariable
        String framework
    );

    @RequestMapping(
        value = "/gen/servers/{framework}",
        method = RequestMethod.POST
    )
    @Operation(
        operationId = "generateServerForLanguage",
        summary = "生成服务端代码",
        description = "根据`OpenAPI文档`和`代码生成配置选项`生成服务端代码。" +
            "可以根据返回的`code`调用`GET /gen/download/{fileId}`接口下载生成的服务端代码。",
        tags={ "servers", }//,
//        responses = {
//            @ApiResponse(
//                responseCode = "200",
//                description = "操作成功",
//                content = {
//                    @Content(schema=@Schema(title = "", description = ""))
//                }
//            )
//        }
    )
    ResponseEntity<ResponseCode> generateServerForLanguage(
        @Parameter(description = "服务端开发框架",required=true)
        @PathVariable
        String framework,

        @Parameter(description = "代码生成配置选项" ,required=true )
        @Valid
        @RequestBody
        GeneratorInput generatorInput
    );

    // endregion 服务端代码生成相关接口
    // ////////////////////////////////////////////////////////////////////////
    // region 代码下载相关接口

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/gen/download/{fileId}",
        produces = { "application/zip", "application/octet-stream" }
    )
    @Operation(
        operationId = "downloadFile",
        summary = "下载生成的代码",
        description = ""
            + "文件ID来自于 `/gen/clients/{language}` 或者 `/gen/servers/{language}` 的 POST 操作。"
            + "一个文件ID只能用一次, 下载完成后文件就会被删除。"
        ,
        tags={ "clients","servers", }
    )
    @Schema(type = "string", format = "binary", title = "文件内容", description = "返回一个ZIP文件。")
    ResponseEntity<Resource> downloadFile(
        @PathVariable
        @Schema(
            title="文件ID",
            description = "由 `POST /gen/clients/{language}` 或者 `POST /gen/servers/{language}` 生成。"
        )
        String fileId
    );

    // endregion 代码下载相关接口
    // ////////////////////////////////////////////////////////////////////////

}