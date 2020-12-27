package org.wapache.openapi.spring.webmvc.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wapache.openapi.v3.annotations.ExternalDocumentation;
import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.Parameter;
import org.wapache.openapi.v3.annotations.extensions.Extension;
import org.wapache.openapi.v3.annotations.parameters.ApiRequestBody;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;
import org.wapache.openapi.v3.annotations.security.SecurityRequirement;
import org.wapache.openapi.v3.annotations.servers.Server;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
@Operation
public @interface MvcOperation {

    // region @RequestMapping
    @AliasFor(
        annotation = RequestMapping.class
    )
    String name() default "";

    @AliasFor(
        attribute = "method",
        annotation = RequestMapping.class
    )
    RequestMethod[] methods() default {};

    @AliasFor(
        annotation = RequestMapping.class
    )
    String[] path() default {};

    @AliasFor(
        annotation = RequestMapping.class
    )
    String[] params() default {};

    @AliasFor(
        annotation = RequestMapping.class
    )
    String[] headers() default {};

    @AliasFor(
        annotation = RequestMapping.class
    )
    String[] consumes() default {};

    @AliasFor(
        annotation = RequestMapping.class
    )
    String[] produces() default {};

    // endregion @RequestMapping

    // region @Operation
    @AliasFor(
        annotation = Operation.class
    )
    String method() default "";

    @AliasFor(
        annotation = Operation.class
    )
    String[] tags() default {};

    @AliasFor(
        attribute = "summary",
        annotation = Operation.class
    )
    String title() default "";

    @AliasFor(
        annotation = Operation.class
    )
    String description() default "";

    @AliasFor(
        annotation = Operation.class
    )
    ApiRequestBody requestBody() default @ApiRequestBody();

    @AliasFor(
        annotation = Operation.class
    )
    ExternalDocumentation externalDocs() default @ExternalDocumentation();

    @AliasFor(
        annotation = Operation.class
    )
    String operationId() default "";

    @AliasFor(
        annotation = Operation.class
    )
    Parameter[] parameters() default {};

    @AliasFor(
        annotation = Operation.class
    )
    ApiResponse[] responses() default {};

    @AliasFor(
        annotation = Operation.class
    )
    boolean deprecated() default false;

    @AliasFor(
        annotation = Operation.class
    )
    SecurityRequirement[] security() default {};

    @AliasFor(
        annotation = Operation.class
    )
    Server[] servers() default {};

    @AliasFor(
        annotation = Operation.class
    )
    Extension[] extensions() default {};

    @AliasFor(
        annotation = Operation.class
    )
    boolean hidden() default false;

    @AliasFor(
        annotation = Operation.class
    )
    boolean ignoreJsonView() default false;

    // endregion @Operation
}
