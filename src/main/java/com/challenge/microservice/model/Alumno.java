package com.challenge.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa un objeto Alumno con información como ID, nombre, apellido, estado
 * y edad.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

	/**
	 * Identificador único del alumno.
	 */
	private Long id;

	/**
	 * Nombre del alumno.
	 */
	private String nombre;

	/**
	 * Apellido del alumno.
	 */
	private String apellido;

	/**
	 * Estado actual del alumno.
	 */
	private Estado estado;

	/**
	 * Edad del alumno.
	 */
	private int edad;

}
