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

package org.wapache.openapi.spring.webmvc.core;

import java.util.List;
import java.util.Optional;

import org.wapache.openapi.spring.core.AbstractRequestBuilder;
import org.wapache.openapi.spring.core.ActuatorProvider;
import org.wapache.openapi.spring.core.GenericResponseBuilder;
import org.wapache.openapi.spring.core.GroupedOpenApi;
import org.wapache.openapi.spring.core.MultipleOpenApiSupportCondition;
import org.wapache.openapi.spring.core.OpenAPIBuilder;
import org.wapache.openapi.spring.core.OperationBuilder;
import org.wapache.openapi.spring.core.RepositoryRestResourceProvider;
import org.wapache.openapi.spring.core.SecurityOAuth2Provider;
import org.wapache.openapi.spring.core.SpringDocConfigProperties;
import org.wapache.openapi.spring.webmvc.api.MultipleOpenApiResource;
import org.wapache.openapi.spring.webmvc.api.RouterFunctionProvider;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import static org.wapache.openapi.spring.core.Constants.SPRINGDOC_ENABLED;


/**
 * The type Multiple open api support configuration.
 * @author bnasslahsen
 */
@Configuration//(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(name = SPRINGDOC_ENABLED, matchIfMissing = true)
@Conditional(MultipleOpenApiSupportCondition.class)
public class MultipleOpenApiSupportConfiguration {

	/**
	 * Multiple open api resource multiple open api resource.
	 *
	 * @param groupedOpenApis the grouped open apis  
	 * @param defaultOpenAPIBuilder the default open api builder  
	 * @param requestBuilder the request builder  
	 * @param responseBuilder the response builder  
	 * @param operationParser the operation parser  
	 * @param requestMappingHandlerMapping the request mapping handler mapping  
	 * @param actuatorProvider the actuator provider  
	 * @param springDocConfigProperties the spring doc config properties  
	 * @param springSecurityOAuth2Provider the spring security o auth 2 provider  
	 * @param routerFunctionProvider the router function provider  
	 * @param repositoryRestResourceProvider the repository rest resource provider  
	 * @return the multiple open api resource
	 */
	@Bean
	@ConditionalOnMissingBean
	@Lazy(false)
    MultipleOpenApiResource multipleOpenApiResource(List<GroupedOpenApi> groupedOpenApis,
                                                    ObjectFactory<OpenAPIBuilder> defaultOpenAPIBuilder, AbstractRequestBuilder requestBuilder,
                                                    GenericResponseBuilder responseBuilder, OperationBuilder operationParser,
                                                    RequestMappingInfoHandlerMapping requestMappingHandlerMapping,
                                                    Optional<ActuatorProvider> actuatorProvider,
                                                    SpringDocConfigProperties springDocConfigProperties,
                                                    Optional<SecurityOAuth2Provider> springSecurityOAuth2Provider,
                                                    Optional<RouterFunctionProvider> routerFunctionProvider,
                                                    Optional<RepositoryRestResourceProvider> repositoryRestResourceProvider) {
		return new MultipleOpenApiResource(groupedOpenApis,
				defaultOpenAPIBuilder, requestBuilder,
				responseBuilder, operationParser,
				requestMappingHandlerMapping, actuatorProvider,
				springDocConfigProperties,
				springSecurityOAuth2Provider,
				routerFunctionProvider,repositoryRestResourceProvider);
	}
}