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

package org.wapache.openapi.spring.core.ui;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.wapache.openapi.spring.core.Constants;
import org.wapache.openapi.spring.core.SpringDocConfiguration;
import org.wapache.openapi.spring.core.SpringDocPropertiesUtils;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;
import static org.wapache.openapi.spring.core.Constants.*;

/**
 * The type OpenAPI ui config parameters.
 * @author bnasslahsen
 */
@Configuration//(proxyBeanMethods = false)
@ConditionalOnProperty(name = SPRINGDOC_OPENAPI_UI_ENABLED, matchIfMissing = true)
@ConditionalOnBean(SpringDocConfiguration.class)
public class OpenApiUiConfigParameters extends AbstractOpenApiUiConfigProperties {

	/**
	 * The constant CONFIG_URL_PROPERTY.
	 */
	public static final String CONFIG_URL_PROPERTY = "configUrl";

	/**
	 * The constant LAYOUT_PROPERTY.
	 */
	public static final String LAYOUT_PROPERTY = "layout";

	/**
	 * The constant FILTER_PROPERTY.
	 */
	public static final String FILTER_PROPERTY = "filter";

	/**
	 * The constant URLS_PROPERTY.
	 */
	public static final String URLS_PROPERTY = "urls";

	/**
	 * The constant OAUTH2_REDIRECT_URL_PROPERTY.
	 */
	public static final String OAUTH2_REDIRECT_URL_PROPERTY = "oauth2RedirectUrl";

	/**
	 * The Ui root path.
	 */
	private String uiRootPath;

	/**
	 * The Swagger ui config.
	 */
	private final OpenApiUiConfigProperties openApiUiConfig;

	/**
	 * Instantiates a new Swagger ui config parameters.
	 *
	 * @param openApiUiConfig the openApi ui config
	 */
	public OpenApiUiConfigParameters(OpenApiUiConfigProperties openApiUiConfig) {
		this.openApiUiConfig = openApiUiConfig;
		this.path = StringUtils.defaultIfBlank(openApiUiConfig.getPath(), Constants.DEFAULT_OPENAPI_UI_PATH);
		this.oauth2RedirectUrl = StringUtils.defaultIfBlank(openApiUiConfig.getOauth2RedirectUrl(), OPENAPI_UI_OAUTH_REDIRECT_URL);
		this.layout = openApiUiConfig.getLayout();
		this.configUrl = openApiUiConfig.getConfigUrl();
		this.validatorUrl = openApiUiConfig.getValidatorUrl();
		this.filter = openApiUiConfig.getFilter();
		this.operationsSorter = openApiUiConfig.getOperationsSorter();
		this.tagsSorter = openApiUiConfig.getTagsSorter();
		this.deepLinking = openApiUiConfig.getDeepLinking();
		this.displayOperationId = openApiUiConfig.getDisplayOperationId();
		this.defaultModelExpandDepth = openApiUiConfig.getDefaultModelExpandDepth();
		this.defaultModelsExpandDepth = openApiUiConfig.getDefaultModelsExpandDepth();
		this.defaultModelRendering = openApiUiConfig.getDefaultModelRendering();
		this.displayRequestDuration = openApiUiConfig.getDisplayRequestDuration();
		this.docExpansion = openApiUiConfig.getDocExpansion();
		this.maxDisplayedTags = openApiUiConfig.getMaxDisplayedTags();
		this.showCommonExtensions = openApiUiConfig.getShowCommonExtensions();
		this.showExtensions = openApiUiConfig.getShowExtensions();
		this.supportedSubmitMethods = openApiUiConfig.getSupportedSubmitMethods();
		this.url = openApiUiConfig.getUrl();
		this.urls = openApiUiConfig.getUrls() == null ? new HashSet<>() : openApiUiConfig.getUrls();
		this.urlsPrimaryName = openApiUiConfig.getUrlsPrimaryName();
		this.groupsOrder = openApiUiConfig.getGroupsOrder();
	}

	/**
	 * Add group.
	 *
	 * @param group the group
	 */
	public void addGroup(String group) {
		OpenApiUrl openApiUrl = new OpenApiUrl(group);
		urls.add(openApiUrl);
	}

	/**
	 * Add url.
	 *
	 * @param url the url
	 */
	public void addUrl(String url) {
		this.urls.forEach(elt ->
				{
					if (!isSwaggerUrlDefined(elt.getName()))
						elt.setUrl(url + DEFAULT_PATH_SEPARATOR + elt.getName());
				}
		);
	}

	/**
	 * Gets ui root path.
	 *
	 * @return the ui root path
	 */
	public String getUiRootPath() {
		return uiRootPath;
	}

	/**
	 * Sets ui root path.
	 *
	 * @param uiRootPath the ui root path
	 */
	public void setUiRootPath(String uiRootPath) {
		this.uiRootPath = uiRootPath;
	}

	/**
	 * Is valid url boolean.
	 *
	 * @param url the url
	 * @return the boolean
	 */
	public boolean isValidUrl(String url) {
		try {
			new URL(url).toURI();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * Gets config parameters.
	 *
	 * @return the config parameters
	 */
	public Map<String, Object> getConfigParameters() {
		final Map<String, Object> params = new TreeMap<>();
		// empty-string prevents openApi-ui default validation
		params.put("validatorUrl", validatorUrl != null ? validatorUrl : "");
		SpringDocPropertiesUtils.put(CONFIG_URL_PROPERTY, configUrl, params);
		SpringDocPropertiesUtils.put("deepLinking", this.deepLinking, params);
		SpringDocPropertiesUtils.put("displayOperationId", displayOperationId, params);
		SpringDocPropertiesUtils.put("defaultModelsExpandDepth", defaultModelsExpandDepth, params);
		SpringDocPropertiesUtils.put("defaultModelExpandDepth", defaultModelExpandDepth, params);
		SpringDocPropertiesUtils.put("defaultModelRendering", defaultModelRendering, params);
		SpringDocPropertiesUtils.put("displayRequestDuration", displayRequestDuration, params);
		SpringDocPropertiesUtils.put("docExpansion", docExpansion, params);
		SpringDocPropertiesUtils.put("maxDisplayedTags", maxDisplayedTags, params);
		SpringDocPropertiesUtils.put("showExtensions", showExtensions, params);
		SpringDocPropertiesUtils.put("showCommonExtensions", showCommonExtensions, params);
		SpringDocPropertiesUtils.put("operationsSorter", operationsSorter, params);
		SpringDocPropertiesUtils.put("tagsSorter", tagsSorter, params);
		if (supportedSubmitMethods!=null)
			SpringDocPropertiesUtils.put("supportedSubmitMethods", supportedSubmitMethods.toString(), params);
		SpringDocPropertiesUtils.put(OAUTH2_REDIRECT_URL_PROPERTY, oauth2RedirectUrl, params);
		SpringDocPropertiesUtils.put("url", url, params);
		put(URLS_PROPERTY, urls, params);
		SpringDocPropertiesUtils.put("urls.primaryName", urlsPrimaryName, params);
		return params;
	}

	/**
	 * Put.
	 *
	 * @param urls the urls
	 * @param openApiUrls the openApi urls
	 * @param params the params
	 */
	private void put(String urls, Set<OpenApiUrl> openApiUrls, Map<String, Object> params) {
		Comparator<OpenApiUrl> openApiUrlComparator;
		if (groupsOrder.isAscending())
			openApiUrlComparator = Comparator.comparing(OpenApiUrl::getName);
		else
			openApiUrlComparator = (h1, h2) -> h2.getName().compareTo(h1.getName());

		openApiUrls = openApiUrls.stream().sorted(openApiUrlComparator).filter(elt -> StringUtils.isNotEmpty(elt.getUrl())).collect(Collectors.toCollection(LinkedHashSet::new));
		if (!CollectionUtils.isEmpty(openApiUrls)) {
			params.put(urls, openApiUrls);
		}
	}

	/**
	 * Is openApi url defined boolean.
	 *
	 * @param name the name
	 * @return the boolean
	 */
	private boolean isSwaggerUrlDefined(String name) {
		if (!CollectionUtils.isEmpty(openApiUiConfig.getUrls()))
			return openApiUiConfig.getUrls().stream().anyMatch(openApiUrl -> name.equals(openApiUrl.getName()) && StringUtils.isNotBlank(openApiUrl.getUrl()));
		return false;
	}
}