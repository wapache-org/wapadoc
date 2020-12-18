package org.wapache.openapi.server.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.Properties;
//
//
//@Configuration
//public class OpenApiConfiguration {
//
//    ApiInfo apiInfo() {
//        final Properties properties = new Properties();
//        try (InputStream stream = this.getClass().getResourceAsStream("/version.properties")) {
//            if (stream != null) {
//                properties.load(stream);
//            }
//        } catch (IOException ex) {
//            // ignore
//        }
//
//        String version = properties.getProperty("version", "unknown");
//
//        return new ApiInfoBuilder()
//            .title("OpenAPI Generator Online")
//            .description("This is an online openapi generator server.  You can find out more at https://github.com/OpenAPITools/openapi-generator.")
//            .license("Apache 2.0")
//            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
//            .termsOfServiceUrl("")
//            .version(version)
//            .contact(new Contact("","", ""))
//            .build();
//    }
//
//    @Bean
//    public Docket customImplementation(){
//        return new Docket(DocumentationType.SWAGGER_2)
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("org.openapitools.codegen.online.api"))
//            .build()
//            .forCodeGeneration(true)
//            .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
//            .directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class)
//            .directModelSubstitute(JsonNode.class, java.lang.Object.class)
//            .ignoredParameterTypes(Resource.class)
//            .ignoredParameterTypes(InputStream.class)
//            .apiInfo(apiInfo());
//    }
//
//}
