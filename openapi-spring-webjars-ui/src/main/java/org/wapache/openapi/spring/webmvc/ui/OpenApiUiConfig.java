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
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wapache.openapi.spring.core.*;
import org.wapache.openapi.spring.core.ui.OpenApiUiConfigParameters;
import org.wapache.openapi.spring.core.ui.OpenApiUiConfigProperties;
import org.wapache.openapi.spring.core.ui.OpenApiUiOAuthProperties;

import static org.wapache.openapi.spring.core.Constants.SPRINGDOC_OPENAPI_UI_ENABLED;


/**
 * The type OpenApi config.
 * @author bnasslahsen
 */
@Configuration//(proxyBeanMethods = false)
@ConditionalOnProperty(name = SPRINGDOC_OPENAPI_UI_ENABLED, matchIfMissing = true)
@ConditionalOnBean(SpringDocConfiguration.class)
public class OpenApiUiConfig {

	/**
	 * OpenApi welcome openApi welcome.
	 *
	 * @param openApiUiConfig the openApi ui config
	 * @param springDocConfigProperties the spring doc config properties
	 * @param openApiUiConfigParameters the openApi ui config parameters
	 * @return the openApi welcome
	 */
	@Bean
	@ConditionalOnMissingBean
	OpenApiWelcome welcome(OpenApiUiConfigProperties openApiUiConfig, SpringDocConfigProperties springDocConfigProperties, OpenApiUiConfigParameters openApiUiConfigParameters) {
		return new OpenApiWelcome(openApiUiConfig, springDocConfigProperties,openApiUiConfigParameters);
	}

	/**
	 * Index page transformer openApi index transformer.
	 *
	 * @param openApiUiConfig the openApi ui config
	 * @param openApiUiOAuthProperties the openApi ui o auth properties
	 * @param objectMapper the object mapper
	 * @return the openApi index transformer
	 */
	@Bean
	@ConditionalOnMissingBean
	OpenApiIndexTransformer indexPageTransformer(OpenApiUiConfigProperties openApiUiConfig, OpenApiUiOAuthProperties openApiUiOAuthProperties, ObjectMapper objectMapper) {
		return new OpenApiIndexPageTransformer(openApiUiConfig, openApiUiOAuthProperties, objectMapper);
	}

	/**
	 * OpenApi web mvc configurer openApi web mvc configurer.
	 *
	 * @param openApiUiConfigParameters the openApi ui calculated config
	 * @param openApiIndexTransformer the openApi index transformer
	 * @return the openApi web mvc configurer
	 */
	@Bean
	@ConditionalOnMissingBean
	OpenApiWebMvcConfigurer webMvcConfigurer(OpenApiUiConfigParameters openApiUiConfigParameters, OpenApiIndexTransformer openApiIndexTransformer) {
		return new OpenApiWebMvcConfigurer(openApiUiConfigParameters, openApiIndexTransformer);
	}

	/**
	 * OpenApi ui config parameters openApi ui config parameters.
	 *
	 * @param openApiUiConfig the openApi ui config
	 * @return the openApi ui config parameters
	 */
	@Bean
	@ConditionalOnMissingBean
	OpenApiUiConfigParameters uiConfigParameters (OpenApiUiConfigProperties openApiUiConfig){
		return new OpenApiUiConfigParameters(openApiUiConfig);
	}
}
