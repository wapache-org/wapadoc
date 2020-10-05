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

package org.wapache.openapi.spring.ui;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;
import org.wapache.openapi.spring.core.SpringDocConfigProperties;
import org.wapache.openapi.spring.core.ui.OpenApiUiConfigParameters;
import org.wapache.openapi.spring.core.ui.OpenApiUiConfigProperties;

import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;
import static org.wapache.openapi.spring.core.Constants.OPENAPI_CONFIG_FILE;


/**
 * The type Abstract OpenAPI welcome.
 * @author bnasslahsen
 */
public abstract class AbstractOpenApiWelcome implements InitializingBean {

	/**
	 * The OpenApi ui configuration.
	 */
	protected final OpenApiUiConfigProperties openApiUiConfig;

	/**
	 * The Spring doc config properties.
	 */
	protected final SpringDocConfigProperties springDocConfigProperties;

	/**
	 * The OpenApi ui calculated config.
	 */
	protected final OpenApiUiConfigParameters openApiUiConfigParameters;


	/**
	 * Instantiates a new Abstract openApi welcome.
	 *
	 * @param openApiUiConfig the openApi ui config
	 * @param springDocConfigProperties the spring doc config properties
	 * @param openApiUiConfigParameters the openApi ui config parameters
	 */
	public AbstractOpenApiWelcome(OpenApiUiConfigProperties openApiUiConfig, SpringDocConfigProperties springDocConfigProperties, OpenApiUiConfigParameters openApiUiConfigParameters) {
		this.openApiUiConfig = openApiUiConfig;
		this.springDocConfigProperties = springDocConfigProperties;
		this.openApiUiConfigParameters = openApiUiConfigParameters;
	}

	@Override
	public void afterPropertiesSet() {
		springDocConfigProperties.getGroupConfigs().forEach(groupConfig -> openApiUiConfigParameters.addGroup(groupConfig.getGroup()));
		calculateUiRootPath();
	}

	/**
	 * Build url string.
	 *
	 * @param contextPath the context path
	 * @param docsUrl the docs url
	 * @return the string
	 */
	protected String buildUrl(String contextPath, final String docsUrl) {
		if (contextPath.endsWith(DEFAULT_PATH_SEPARATOR)) {
			return contextPath.substring(0, contextPath.length() - 1) + docsUrl;
		}
		return contextPath + docsUrl;
	}

	/**
	 * Build config url.
	 *
	 * @param contextPath the context path
	 * @param uriComponentsBuilder the uri components builder
	 */
	protected void buildConfigUrl(String contextPath, UriComponentsBuilder uriComponentsBuilder) {
		String apiDocsUrl = springDocConfigProperties.getApiDocs().getPath();
		if (StringUtils.isEmpty(openApiUiConfig.getConfigUrl())) {
			String url = buildUrl(contextPath, apiDocsUrl);
			String openApiConfigUrl = url + DEFAULT_PATH_SEPARATOR + OPENAPI_CONFIG_FILE;
			openApiUiConfigParameters.setConfigUrl(openApiConfigUrl);
			if (CollectionUtils.isEmpty(openApiUiConfigParameters.getUrls())) {
				String openApiUiUrl = openApiUiConfig.getUrl();
				if (StringUtils.isEmpty(openApiUiUrl))
					openApiUiConfigParameters.setUrl(url);
				else
					openApiUiConfigParameters.setUrl(openApiUiUrl);
			}
			else
				openApiUiConfigParameters.addUrl(url);
		}
		calculateOauth2RedirectUrl(uriComponentsBuilder);
	}

	/**
	 * Gets uri components builder.
	 *
	 * @param sbUrl the sb url
	 * @return the uri components builder
	 */
	protected UriComponentsBuilder getUriComponentsBuilder(String sbUrl) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(sbUrl);
		if ( //
			openApiUiConfig.isDisplayQueryParams() && StringUtils.isNotEmpty(openApiUiConfigParameters.getUrl())
		){
			openApiUiConfigParameters.getConfigParameters().entrySet().stream()
			.filter(entry -> !OpenApiUiConfigParameters.CONFIG_URL_PROPERTY.equals(entry.getKey()))
			.filter(entry -> !entry.getKey().startsWith(OpenApiUiConfigParameters.URLS_PROPERTY))
			.filter(entry -> StringUtils.isNotEmpty((String) entry.getValue()))
			.forEach(entry -> uriBuilder.queryParam(entry.getKey(), entry.getValue()));
		} else if ( //
			openApiUiConfig.isDisplayQueryParamsWithoutOauth2() && StringUtils.isNotEmpty(openApiUiConfigParameters.getUrl())
		){
			openApiUiConfigParameters.getConfigParameters().entrySet().stream()
			.filter(entry -> !OpenApiUiConfigParameters.CONFIG_URL_PROPERTY.equals(entry.getKey()))
			.filter(entry -> !OpenApiUiConfigParameters.OAUTH2_REDIRECT_URL_PROPERTY.equals(entry.getKey()))
			.filter(entry -> !entry.getKey().startsWith(OpenApiUiConfigParameters.URLS_PROPERTY))
			.filter(entry -> StringUtils.isNotEmpty((String) entry.getValue()))
			.forEach(entry -> uriBuilder.queryParam(entry.getKey(), entry.getValue()));
		} else {
			uriBuilder.queryParam(OpenApiUiConfigParameters.CONFIG_URL_PROPERTY, openApiUiConfigParameters.getConfigUrl());
			if (StringUtils.isNotEmpty(openApiUiConfigParameters.getLayout())) {
				uriBuilder.queryParam(OpenApiUiConfigParameters.LAYOUT_PROPERTY, openApiUiConfigParameters.getLayout());
			}
			if (openApiUiConfigParameters.getFilter() != null) {
				uriBuilder.queryParam(OpenApiUiConfigParameters.FILTER_PROPERTY, openApiUiConfigParameters.getFilter());
			}
		}
		return uriBuilder;
	}

	/**
	 * Calculate oauth 2 redirect url.
	 *
	 * @param uriComponentsBuilder the uri components builder
	 */
	protected abstract void calculateOauth2RedirectUrl(UriComponentsBuilder uriComponentsBuilder);

	/**
	 * Calculate ui root path.
	 *
	 * @param sbUrls the sb urls
	 */
	protected abstract void calculateUiRootPath(StringBuilder... sbUrls);

}