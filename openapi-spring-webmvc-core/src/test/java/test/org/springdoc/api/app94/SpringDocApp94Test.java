package test.org.springdoc.api.app94;

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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.wapache.openapi.v3.annotations.Operation;
import org.wapache.openapi.v3.annotations.responses.ApiResponse;
import org.wapache.openapi.v3.annotations.responses.ApiResponses;
import org.wapache.openapi.v3.annotations.tags.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.wapache.openapi.spring.core.AbstractRequestBuilder;
import org.wapache.openapi.spring.core.ActuatorProvider;
import org.wapache.openapi.spring.core.GenericResponseBuilder;
import org.wapache.openapi.spring.core.OpenAPIBuilder;
import org.wapache.openapi.spring.core.OperationBuilder;
import org.wapache.openapi.spring.core.RepositoryRestResourceProvider;
import org.wapache.openapi.spring.core.SecurityOAuth2Provider;
import org.wapache.openapi.spring.core.SpringDocConfigProperties;
import org.wapache.openapi.spring.core.customizers.OpenApiBuilderCustomiser;
import org.wapache.openapi.spring.core.customizers.OpenApiCustomiser;
import org.wapache.openapi.spring.core.customizers.OperationCustomizer;
import org.wapache.openapi.spring.webmvc.api.OpenApiResource;
import org.wapache.openapi.spring.webmvc.api.RouterFunctionProvider;
import test.org.springdoc.api.AbstractSpringDocTest;
import test.org.springdoc.api.app91.Greeting;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.wapache.openapi.spring.core.Constants.DEFAULT_GROUP_NAME;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@TestPropertySource(properties = "springdoc.default-produces-media-type=application/json")
public class SpringDocApp94Test extends AbstractSpringDocTest {

	@SpringBootApplication
	static class SpringDocTestApp implements ApplicationContextAware {

		private ApplicationContext applicationContext;

		@Bean
		public GreetingController greetingController() {
			return new GreetingController();
		}

		@Bean
		public OpenApiBuilderCustomiser customOpenAPI() {
			return openApiBuilder -> openApiBuilder.addMappings(Collections.singletonMap("greetingController", new GreetingController()));
		}

		@Bean
		public RequestMappingHandlerMapping defaultTestHandlerMapping(GreetingController greetingController) throws NoSuchMethodException {
			RequestMappingHandlerMapping result = new RequestMappingHandlerMapping();
			RequestMappingInfo requestMappingInfo =
					RequestMappingInfo.paths("/test").methods(RequestMethod.GET).produces(MediaType.APPLICATION_JSON_VALUE).build();

			result.setApplicationContext(this.applicationContext);
			result.registerMapping(requestMappingInfo, "greetingController", GreetingController.class.getDeclaredMethod("sayHello2"));
			//result.handlerme
			return result;
		}

		@Bean(name = "openApiResource")
		public OpenApiResource openApiResource(ObjectFactory<OpenAPIBuilder> openAPIBuilderObjectFactory, AbstractRequestBuilder requestBuilder, GenericResponseBuilder responseBuilder,
				OperationBuilder operationParser,Optional<List<OperationCustomizer>> operationCustomizers,
				@Qualifier("defaultTestHandlerMapping") RequestMappingHandlerMapping requestMappingHandlerMapping,
				Optional<ActuatorProvider> actuatorProvider, SpringDocConfigProperties springDocConfigProperties,
				Optional<List<OpenApiCustomiser>> openApiCustomisers, Optional<SecurityOAuth2Provider> springSecurityOAuth2Provider,
				Optional<RouterFunctionProvider> routerFunctionProvider, Optional<RepositoryRestResourceProvider> repositoryRestResourceProvider) {
			return new OpenApiResource(DEFAULT_GROUP_NAME, openAPIBuilderObjectFactory, requestBuilder, responseBuilder, operationParser, requestMappingHandlerMapping,
					actuatorProvider,operationCustomizers, openApiCustomisers, springDocConfigProperties, springSecurityOAuth2Provider,routerFunctionProvider, repositoryRestResourceProvider);
		}

		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			this.applicationContext = applicationContext;
		}
	}

	@ResponseBody
	@Tag(name = "Demo", description = "The Demo API")
	public static class GreetingController {

		@GetMapping(produces = APPLICATION_JSON_VALUE)
		@Operation(summary = "This API will return a random greeting.")
		public ResponseEntity<Greeting> sayHello() {
			return ResponseEntity.ok(new Greeting(RandomStringUtils.randomAlphanumeric(10)));
		}

		@GetMapping("/test")
		@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "item created"),
				@ApiResponse(responseCode = "400", description = "invalid input, object invalid"),
				@ApiResponse(responseCode = "409", description = "an existing item already exists") })
		public ResponseEntity<Greeting> sayHello2() {
			return ResponseEntity.ok(new Greeting(RandomStringUtils.randomAlphanumeric(10)));
		}

	}
}