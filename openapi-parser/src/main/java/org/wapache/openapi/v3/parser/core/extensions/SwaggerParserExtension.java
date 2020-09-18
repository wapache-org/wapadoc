package org.wapache.openapi.v3.parser.core.extensions;

import org.wapache.openapi.v3.parser.core.models.AuthorizationValue;
import org.wapache.openapi.v3.parser.core.models.ParseOptions;
import org.wapache.openapi.v3.parser.core.models.SwaggerParseResult;

import java.util.List;

public interface SwaggerParserExtension {
    SwaggerParseResult readLocation(String url, List<AuthorizationValue> auth, ParseOptions options);

    SwaggerParseResult readContents(String swaggerAsString, List<AuthorizationValue> auth, ParseOptions options);
}
