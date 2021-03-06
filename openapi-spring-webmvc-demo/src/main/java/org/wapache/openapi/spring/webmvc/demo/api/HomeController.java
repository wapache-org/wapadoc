/*
 *
 *  * Copyright 2019-2020 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.wapache.openapi.spring.webmvc.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.wapache.openapi.spring.core.ui.OpenApiUiConfigParameters;
import org.wapache.openapi.spring.core.ui.SwaggerUiConfigParameters;
import org.wapache.openapi.v3.annotations.Hidden;

import static org.wapache.openapi.spring.core.Constants.SWAGGER_UI_PATH;
import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;
import static org.springframework.web.servlet.view.UrlBasedViewResolver.REDIRECT_URL_PREFIX;

/**
 * Home redirection to swagger api documentation
 */
@Controller
@Hidden
public class HomeController {

	@Autowired(required = false)
	OpenApiUiConfigParameters openApiUiConfigParameters;

	@Autowired(required = false)
	SwaggerUiConfigParameters swaggerUiConfigParameters;

	@GetMapping(DEFAULT_PATH_SEPARATOR)
	public String index() {
		return REDIRECT_URL_PREFIX + (
			openApiUiConfigParameters!=null?openApiUiConfigParameters.getPath():
				swaggerUiConfigParameters!=null? swaggerUiConfigParameters.getPath():
					"404"
			);
	}
}
