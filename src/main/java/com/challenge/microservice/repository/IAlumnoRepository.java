package com.challenge.microservice.repository;

import com.challenge.microservice.model.Alumno;
import com.challenge.microservice.model.Estado;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz que define operaciones de acceso a datos para la entidad Alumno.
 */
public interface IAlumnoRepository {

	/**
	 * Guarda un objeto Alumno en el repositorio.
	 *
	 * @param alumno El objeto Alumno a guardar.
	 * @return Un Mono que representa la finalización exitosa de la operación.
	 */
	public Mono<Void> save(Alumno alumno);

	/**
	 * Recupera una página de objetos Alumno filtrados por estado.
	 *
	 * @param estado Estado por el cual filtrar los alumnos.
	 * @param page   Número de página (basado en cero) a recuperar.
	 * @param size   Tamaño de la página (cantidad de elementos por página).
	 * @return Un Flux que emite objetos Alumno que cumplen con los criterios de
	 *         filtrado y paginación.
	 */
	public Flux<Alumno> findByEstadoPaged(Estado estado, int page, int size);

	/**
	 * Calcula la cantidad de objetos Alumno con el estado dado.
	 *
	 * @param estado Estado por el cual contar los alumnos.
	 * @return Un Mono que emite la cantidad de alumnos que cumplen con el estado
	 *         dado.
	 */
	public Mono<Long> countByEstado(Estado estado);

	/**
	 * Verifica si existe un objeto Alumno con el ID dado.
	 *
	 * @param id Identificador único del alumno.
	 * @return Un Mono que emite true si existe un alumno con el ID dado, false en
	 *         caso contrario.
	 */
	public Mono<Boolean> existsById(Long id);
}
