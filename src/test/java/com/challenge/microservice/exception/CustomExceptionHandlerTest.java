package com.challenge.microservice.exception;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class CustomExceptionHandlerTest {

	private CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();

	@Test
	void testHandleDuplicateIdException() {
		DuplicateIdException exception = new DuplicateIdException(123L);

		StepVerifier.create(customExceptionHandler.handleDuplicateIdException(exception))
				.expectNext(Map.of("message", "No se pudo hacer la grabación porque ya existe el id 123"))
				.verifyComplete();
	}

	@Test
	void testHandleWebExchangeBindException() {
		BindingResult bindingResult = new BeanPropertyBindingResult(null, "alumno");
		for (FieldError fieldError : List.of(new FieldError("alumno", "nombre", "no debe ser nulo"))) {
			bindingResult.addError(fieldError);
		}

		Mono<Object> responseMono = customExceptionHandler
				.handleWebExchangeBindException(new WebExchangeBindException(null, bindingResult));

		StepVerifier.create(responseMono).expectNext(Map.of("message", List.of("El campo nombre no debe ser nulo")))
				.verifyComplete();
	}

	@Test
	void testHandleWebExchangeBindExceptionContains() {
		BindingResult bindingResult = new BeanPropertyBindingResult(null, "alumno");
		for (FieldError fieldError : List.of(new FieldError("alumno", "nombre", "El campo nombre no debe ser vacío"))) {
			bindingResult.addError(fieldError);
		}

		Mono<Object> responseMono = customExceptionHandler
				.handleWebExchangeBindException(new WebExchangeBindException(null, bindingResult));

		StepVerifier.create(responseMono).expectNext(Map.of("message", List.of("El campo nombre no debe ser vacío")))
				.verifyComplete();
	}

	@Test
	void testHandleConstraintViolationException() {
		ConstraintViolationException exception = mock(ConstraintViolationException.class);
		ConstraintViolation<?> violation = mock(ConstraintViolation.class);

		when(exception.getConstraintViolations()).thenReturn(Set.of(violation));
		when(violation.getMessage()).thenReturn("El parámetro 'size' debe ser mayor o igual a 1");

		// Llamar al método que se está probando
		Mono<Map<String, List<String>>> resultMono = customExceptionHandler
				.handleConstraintViolationException(exception);

		// Utilizar StepVerifier para realizar aserciones reactivas
		StepVerifier.create(resultMono)
				.expectNext(Map.of("message", List.of("El parámetro 'size' debe ser mayor o igual a 1")))
				.verifyComplete();

		// Verificar que se llamó a los métodos esperados
		verify(exception, times(1)).getConstraintViolations();
	}
}
