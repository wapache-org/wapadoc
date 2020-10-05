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

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.util.UriComponentsBuilder;
import org.wapache.openapi.spring.core.SpringDocConfigProperties;
import org.wapache.openapi.spring.core.SpringDocConfiguration;
import org.wapache.openapi.spring.core.ui.OpenApiUiConfigParameters;
import org.wapache.openapi.spring.core.ui.OpenApiUiConfigProperties;
import org.wapache.openapi.spring.ui.AbstractOpenApiWelcome;
import org.wapache.openapi.v3.annotations.Operation;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;
import static org.wapache.openapi.spring.core.Constants.*;

/**
 * The type OpenAPI welcome.
 * @author bnasslahsen
 */
@Controller
public class OpenApiWelcome extends AbstractOpenApiWelcome {

	/**
	 * The Mvc servlet path.
	 */
   // To keep compatiblity with spring-boot 1 - WebMvcProperties changed package from srping 4 to spring 5
	@Value(MVC_SERVLET_PATH)
	private String mvcServletPath;

	/**
	 * Instantiates a new OpenApi welcome.
	 *
	 * @param openApiUiConfig the openApi ui config
	 * @param springDocConfigProperties the spring doc config properties
	 * @param openApiUiConfigParameters the openApi ui config parameters
	 */
	public OpenApiWelcome(
		OpenApiUiConfigProperties openApiUiConfig,
		SpringDocConfigProperties springDocConfigProperties,
		OpenApiUiConfigParameters openApiUiConfigParameters
	) {
		super(openApiUiConfig, springDocConfigProperties, openApiUiConfigParameters);
	}

	/**
	 * Redirect to ui string.
	 *
	 * @param request the request
	 * @return the string
	 */
	@Operation(hidden = true)
	@GetMapping(OPENAPI_UI_PATH)
	public String redirectToUi(HttpServletRequest request) {
		buildConfigUrl(request.getContextPath(), ServletUriComponentsBuilder.fromCurrentContextPath());
		String sbUrl =   openApiUiConfigParameters.getUiRootPath() + OPENAPI_UI_URL;
		UriComponentsBuilder uriBuilder = getUriComponentsBuilder(sbUrl);
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX + uriBuilder.build().encode().toString();
	}

	/**
	 * Openapi yaml map.
	 *
	 * @param request the request
	 * @return the map
	 */
	@Operation(hidden = true)
	@GetMapping(value = OPENAPI_CONFIG_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> openapiJson(HttpServletRequest request) {
		buildConfigUrl(request.getContextPath(), ServletUriComponentsBuilder.fromCurrentContextPath());
		return openApiUiConfigParameters.getConfigParameters();
	}

	@Override
	protected void calculateUiRootPath(StringBuilder... sbUrls) {
		StringBuilder sbUrl = new StringBuilder();
		if (StringUtils.isNotBlank(mvcServletPath))
			sbUrl.append(mvcServletPath);
		if (ArrayUtils.isNotEmpty(sbUrls))
			sbUrl = sbUrls[0];
		String openApiPath = openApiUiConfigParameters.getPath();
		if (openApiPath.contains(DEFAULT_PATH_SEPARATOR))
			sbUrl.append(openApiPath, 0, openApiPath.lastIndexOf(DEFAULT_PATH_SEPARATOR));
		openApiUiConfigParameters.setUiRootPath(sbUrl.toString());
	}

	@Override
	protected String buildUrl(String contextPath, final String docsUrl) {
		if (StringUtils.isNotBlank(mvcServletPath))
			contextPath += mvcServletPath;
		return super.buildUrl(contextPath, docsUrl);
	}

	@Override
	protected void calculateOauth2RedirectUrl(UriComponentsBuilder uriComponentsBuilder) {
		if (!openApiUiConfigParameters.isValidUrl(openApiUiConfigParameters.getOauth2RedirectUrl()))
			openApiUiConfigParameters.setOauth2RedirectUrl(uriComponentsBuilder
					.path(openApiUiConfigParameters.getUiRootPath())
					.path(openApiUiConfigParameters.getOauth2RedirectUrl())
					.build().toString());
	}
}