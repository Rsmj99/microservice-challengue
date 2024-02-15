package com.challenge.microservice.exception;

/**
 * Excepción lanzada cuando se intenta realizar una grabación, pero ya existe un
 * registro con el mismo ID.
 */
public class DuplicateIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Construye una instancia de DuplicateIdException con el ID duplicado.
	 *
	 * @param duplicateId El ID que ya existe y causó la excepción.
	 */
	public DuplicateIdException(Long long1) {
		super("No se pudo hacer la grabación porque ya existe el id " + long1);
	}
}
