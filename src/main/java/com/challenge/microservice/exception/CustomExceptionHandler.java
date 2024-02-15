package com.challenge.microservice.exception;

import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que maneja las excepciones personalizadas en la aplicación y
 * proporciona respuestas adecuadas para cada tipo de excepción.
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

	private static final String MESSAGE = "message";

	/**
	 * Maneja la excepción DuplicateIdException y responde con un mensaje de
	 * conflicto.
	 *
	 * @param e La excepción DuplicateIdException que se ha lanzado.
	 * @return Un Mono que contiene un mapa con el mensaje de error.
	 */
	@ExceptionHandler(DuplicateIdException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Mono<Object> handleDuplicateIdException(DuplicateIdException e) {
		log.error("Error de tipo DuplicateIdException: {}", e);
		return Mono.just(Map.of(MESSAGE, e.getMessage()));
	}

	/**
	 * Maneja la excepción WebExchangeBindException y responde con mensajes de error
	 * de validación.
	 *
	 * @param e La excepción WebExchangeBindException que se ha lanzado.
	 * @return Un Mono que contiene un mapa con mensajes de error de validación.
	 */
	@ExceptionHandler(WebExchangeBindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Mono<Object> handleWebExchangeBindException(WebExchangeBindException e) {
		log.error("Error de tipo WebExchangeBindException: {}", e.getFieldErrors());
		return Flux.fromIterable(e.getFieldErrors())
				.map(fieldError -> fieldError.getDefaultMessage().contains(fieldError.getField())
						? fieldError.getDefaultMessage()
						: String.format("El campo %s %s", fieldError.getField(), fieldError.getDefaultMessage()))
				.collectList().flatMap(list -> Mono.just(Map.of(MESSAGE, list)));
	}

	/**
	 * Maneja la excepción ConstraintViolationException y responde con mensajes de
	 * error de validación.
	 *
	 * @param e La excepción ConstraintViolationException que se ha lanzado.
	 * @return Un {@code Mono} que emite un mapa con un solo elemento "message" que
	 *         contiene una lista de mensajes de error generados por las violaciones
	 *         de restricciones de validación.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Mono<Map<String, List<String>>> handleConstraintViolationException(ConstraintViolationException e) {
		log.error("Error de tipo ConstraintViolationException: {}", e);
		return Flux.fromIterable(e.getConstraintViolations()).map(ConstraintViolation::getMessage).collectList()
				.flatMap(list -> Mono.just(Map.of(MESSAGE, list)));
	}
}
