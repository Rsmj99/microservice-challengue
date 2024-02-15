package com.challenge.microservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class MicroserviceApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	// Esta prueba asegura que el contexto de la aplicación se cargue sin errores.
	@Test
	void contextLoads() {
		// Simula la ejecución del método main con una lista de argumentos vacía.
		MicroserviceApplication.main(new String[] {});

		// Asegúrate de que el contexto no sea nulo (puedes personalizar esto según tus
		// necesidades).
		assertNotNull(applicationContext, "El contexto de la aplicación no debería ser nulo.");
	}

}
