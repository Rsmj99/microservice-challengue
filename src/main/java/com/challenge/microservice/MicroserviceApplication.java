package com.challenge.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicación del microservicio.
 */
@SpringBootApplication
public class MicroserviceApplication {

	/**
	 * Punto de entrada principal para iniciar la aplicación.
	 *
	 * @param args Argumentos de la línea de comandos proporcionados al iniciar la
	 *             aplicación.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}
}
