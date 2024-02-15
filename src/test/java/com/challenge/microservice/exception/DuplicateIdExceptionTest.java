package com.challenge.microservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DuplicateIdExceptionTest {

	@Test
	void testConstructor() {
		Long id = 123L;
		DuplicateIdException exception = new DuplicateIdException(id);

		assertEquals("No se pudo hacer la grabación porque ya existe el id " + id, exception.getMessage());
	}
}
