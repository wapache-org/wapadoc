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

package org.wapache.openapi.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.wapache.openapi.generator.CliOption;
import org.wapache.openapi.spring.core.GroupedOpenApi;
import org.wapache.openapi.spring.core.SpringDocUtils;
import org.wapache.openapi.v3.core.converter.AnnotatedType;
import org.wapache.openapi.v3.core.converter.ModelConverters;
import org.wapache.openapi.v3.core.converter.ResolvedSchema;
import org.wapache.openapi.v3.models.Components;
import org.wapache.openapi.v3.models.OpenAPI;
import org.wapache.openapi.v3.models.info.Info;
import org.wapache.openapi.v3.models.info.License;
import org.wapache.openapi.v3.models.media.*;
import org.wapache.openapi.v3.models.security.SecurityScheme;


@Configuration
@SpringBootApplication
//@ComponentScan(basePackages = {
//	"org.wapache.openapi.server",
//	"org.wapache.openapi.server.api",
//	"org.wapache.openapi.server.configuration"
//})
public class OpenApiGeneratorServerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(OpenApiGeneratorServerApplication.class);
	}

	@Bean
	public OpenAPI openApiConfiguration(@Value("${springdoc.version}") String appVersion) {

		SpringDocUtils.getConfig().removeRequestWrapperToIgnore(java.util.Map.class);

		Schema cliOptionSchema = ModelConverters.getInstance()
			.resolveAsResolvedSchema(new AnnotatedType(CliOption.class))
			.schema;
		cliOptionSchema.setTitle("配置选项对象");

		return new OpenAPI()
		.components(new Components()
			.addSecuritySchemes("basicScheme", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
			// 目前的实现无法对`Map<String, CliOption>` getClientOptions();`这种类型的方法生成正确的返回值定义。
			// 需要手工定义一个Schema, 然后在方法里引用它``
			.addSchemas(CliOption.class.getSimpleName(), cliOptionSchema)
			.addSchemas("MapOfOptionNameAndCliOption", new MapSchema()
				.title("配置选项键值对1")
				.description("key是配置项名称, value是配置项对象")
				.additionalProperties(new ObjectSchema()
					.title("配置选项键值对2")
					.description("key是配置项名称, value是配置项对象")
					.$ref("#/components/schemas/"+CliOption.class.getSimpleName())
				)
			)
		)
		.info(new Info()
			.title("OpenAPI代码生成服务")
			.version(appVersion)
			.description("这是一个根据OpenAPI文档生成代码的在线服务, 原始版本参见: https://github.com/OpenAPITools/openapi-generator.")
			.termsOfService("http://wapache.org/terms/")
			.license(new License().name("Apache 2.0").url("http://wapache.org"))
		);
	}

	@Bean
	@Profile("!prod")
	public GroupedOpenApi actuatorOpenApiConfiguration() {
		return GroupedOpenApi.builder().group("Actuator")
		.pathsToMatch("/actuator/**")
		.pathsToExclude("/actuator/health/*")
		.build();
	}

//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//		// Allow anyone and anything access. Probably ok for Swagger spec
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//
//		source.registerCorsConfiguration("/v3/api-docs", config);
//		return new CorsFilter(source);
//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowCredentials(true)
			.allowedMethods("GET", "POST", "DELETE", "PUT")
			.maxAge(3600);
	}

}
