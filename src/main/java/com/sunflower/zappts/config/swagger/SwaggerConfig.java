package com.sunflower.zappts.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket apiWebService() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.sunflower.zappts"))
				.paths(PathSelectors.ant("/**"))
				.build()
				//.ignoredParameterTypes(User.class)
				.globalOperationParameters(
				Arrays.asList(
				new ParameterBuilder()
				.name("Authorization")
				.description("Header para token JWT")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build()))
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Teste Zappts")
				.description("Um exemplo de aplicação Spring Boot REST API")
				.version("1.0.0")
				.license("Apache Lincense Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.contact(new Contact("Jackson Almeida Rodrigues", "https://www.linkedin.com/in/jackson-almeida-424b921b0/", "jacksonnalmeida30@gmail.com"))
				.build();
	}
	
}
