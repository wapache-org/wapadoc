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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.wapache.openapi.spring.core.ui.OpenApiUiConfigParameters;

import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;
import static org.wapache.openapi.spring.core.Constants.CLASSPATH_RESOURCE_LOCATION;
import static org.wapache.openapi.spring.core.Constants.DEFAULT_WEB_JARS_PREFIX_URL;

/**
 * The type OpenApi web mvc configurer.
 * @author bnasslahsen
 */
@SuppressWarnings("deprecation")
public class OpenApiWebMvcConfigurer extends WebMvcConfigurerAdapter { // NOSONAR

	Logger log = LoggerFactory.getLogger(OpenApiWebMvcConfigurer.class);

	/**
	 * The OpenApi path.
	 */
	private String openApiPath;

	/**
	 * The OpenApi index transformer.
	 */
	private OpenApiIndexTransformer openApiIndexTransformer;

	/**
	 * Instantiates a new OpenApi web mvc configurer.
	 *
	 * @param openApiUiConfigParameters the openApi ui calculated config
	 * @param openApiIndexTransformer the openApi index transformer
	 */
	public OpenApiWebMvcConfigurer(OpenApiUiConfigParameters openApiUiConfigParameters, OpenApiIndexTransformer openApiIndexTransformer) {
		this.openApiPath = openApiUiConfigParameters.getPath();
		this.openApiIndexTransformer = openApiIndexTransformer;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		StringBuilder uiRootPath = new StringBuilder();

		if (openApiPath.contains("/")) {
			uiRootPath.append(openApiPath, 0, openApiPath.lastIndexOf('/'));
		}
		uiRootPath.append("/**");

		registry.addResourceHandler(uiRootPath + "/openapi-ui/**")
		.addResourceLocations(CLASSPATH_RESOURCE_LOCATION + DEFAULT_WEB_JARS_PREFIX_URL + DEFAULT_PATH_SEPARATOR)
		.resourceChain(false)
		.addTransformer(openApiIndexTransformer);
	}

}
