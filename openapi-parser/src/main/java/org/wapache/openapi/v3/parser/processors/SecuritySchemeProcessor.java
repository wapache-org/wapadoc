package org.wapache.openapi.v3.parser.processors;

import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.security.SecurityScheme;
import org.wapache.openapi.v3.parser.ResolverCache;
import org.wapache.openapi.v3.parser.models.RefFormat;
import org.wapache.openapi.v3.parser.util.RefUtils;

/**
 * Created by gracekarina on 23/06/17.
 */
public class SecuritySchemeProcessor {
    private final ResolverCache cache;
    private final OpenAPI openAPI;
    private final ExternalRefProcessor externalRefProcessor;

    public SecuritySchemeProcessor(ResolverCache cache, OpenAPI openAPI) {
        this.cache = cache;
        this.openAPI = openAPI;
        this.externalRefProcessor = new ExternalRefProcessor(cache, openAPI);
    }

    public SecurityScheme processSecurityScheme(SecurityScheme securityScheme) {

        if (securityScheme.get$ref() != null){
            RefFormat refFormat = RefUtils.computeRefFormat(securityScheme.get$ref());
            String $ref = securityScheme.get$ref();
            SecurityScheme newSecurityScheme = cache.loadRef($ref, refFormat, SecurityScheme.class);
            if (newSecurityScheme != null) {
                return newSecurityScheme;
            }
        }
        return securityScheme;

    }
}
