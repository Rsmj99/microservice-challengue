package com.challenge.microservice.service;

import com.challenge.microservice.dto.AlumnoDTO;
import com.challenge.microservice.model.PaginatedResponse;

import reactor.core.publisher.Mono;

/**
 * Interfaz que define las operaciones relacionadas con la gestión de alumnos.
 */
public interface IAlumnoService {

	/**
	 * Guarda la información de un nuevo alumno.
	 *
	 * @param alumnoDTO Información del alumno a guardar.
	 * @return Un Mono que representa la finalización exitosa de la operación.
	 */
	Mono<Void> saveAlumno(AlumnoDTO alumnoDTO);

	/**
	 * Obtiene una lista paginada de alumnos activos.
	 *
	 * @param page Número de página (basado en cero) que se desea recuperar.
	 * @param size Tamaño de la página, que determina la cantidad de elementos por
	 *             página.
	 * @return Un Mono que emite una PaginatedResponse de AlumnoDTO, que incluye la
	 *         lista de alumnos activos, el número total de elementos y el número
	 *         total de páginas.
	 */
	Mono<PaginatedResponse<AlumnoDTO>> getAllAlumnosActivos(int page, int size);
}
