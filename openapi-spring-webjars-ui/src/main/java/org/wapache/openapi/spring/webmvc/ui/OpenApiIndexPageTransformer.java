/*
 *
 *  *
 *  *  * Copyright 2019-2020 the original author or authors.
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package org.wapache.openapi.spring.webmvc.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.resource.ResourceTransformerChain;
import org.springframework.web.servlet.resource.TransformedResource;
import org.wapache.openapi.spring.core.ui.OpenApiUiConfigProperties;
import org.wapache.openapi.spring.core.ui.OpenApiUiOAuthProperties;
import org.wapache.openapi.spring.ui.AbstractOpenApiUiIndexTransformer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type OpenApi index transformer.
 * @author bnasslahsen
 */
public class OpenApiIndexPageTransformer extends AbstractOpenApiUiIndexTransformer implements OpenApiIndexTransformer {

	/**
	 * Instantiates a new OpenApi index transformer.
	 *
	 * @param openApiUiConfig the openApi ui config
	 * @param openApiUiOAuthProperties the openApi ui o auth properties
	 * @param objectMapper the object mapper
	 */
	public OpenApiIndexPageTransformer(OpenApiUiConfigProperties openApiUiConfig,
									   OpenApiUiOAuthProperties openApiUiOAuthProperties,
									   ObjectMapper objectMapper) {
		super(openApiUiConfig, openApiUiOAuthProperties, objectMapper);
	}

	@Override
	public Resource transform(
		HttpServletRequest request, Resource resource, ResourceTransformerChain transformerChain
	) throws IOException {
		final AntPathMatcher antPathMatcher = new AntPathMatcher();
		boolean isIndexFound = antPathMatcher.match("**/openapi-ui/**/index.html", resource.getURL().toString());

		if (isIndexFound && hasDefaultTransformations()) {
			String html = defaultTransformations(resource.getInputStream());
			return new TransformedResource(resource, html.getBytes());
		}
		else
			return resource;
	}

}