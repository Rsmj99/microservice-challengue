package com.challenge.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuración de Swagger para la documentación de la API.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Configura Docket, que es el principal punto de entrada para la configuración
	 * de Swagger.
	 *
	 * @return Docket configurado para escanear y documentar controladores en el
	 *         paquete "com.challenge.microservice.controller".
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.challenge.microservice.controller"))
				.paths(PathSelectors.any()).build();
	}
}