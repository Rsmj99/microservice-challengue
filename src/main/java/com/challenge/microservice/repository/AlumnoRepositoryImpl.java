package com.challenge.microservice.repository;

import java.util.Comparator;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;

import com.challenge.microservice.model.Alumno;
import com.challenge.microservice.model.Estado;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repositorio para operaciones relacionadas con los alumnos almacenados en
 * Redis.
 */
@Repository
@AllArgsConstructor
@Slf4j
public class AlumnoRepositoryImpl implements IAlumnoRepository {

	private final ReactiveRedisOperations<String, Alumno> redisOperations;
	private static final String KEY = "alumno";

	/**
	 * Guarda un alumno en la base de datos.
	 *
	 * @param alumno El alumno a guardar.
	 * @return Una operación Mono<Void> que indica la finalización del guardado.
	 */
	public Mono<Void> save(Alumno alumno) {
		log.info("Almacenando en bd al alumno: {}", alumno);
		return redisOperations.opsForHash().put(KEY, alumno.getId().toString(), alumno).then();
	}

	/**
	 * Recupera una página de alumnos filtrados por estado y paginados.
	 *
	 * @param estado Estado por el cual filtrar los alumnos.
	 * @param page   Número de la página a recuperar (comenzando desde 0).
	 * @param size   Tamaño de la página, es decir, la cantidad de elementos por
	 *               página.
	 * @return Un {@link Flux} que emite la página solicitada de alumnos.
	 */
	public Flux<Alumno> findByEstadoPaged(Estado estado, int page, int size) {
		log.info("Recuperando alumnos por paginado con estado: {}", estado);
		return redisOperations.opsForHash().values(KEY).cast(Alumno.class)
				.filter(alumno -> estado.equals(alumno.getEstado())).sort(Comparator.comparing(Alumno::getId))
				.skip((long) page * size).take(size);
	}

	/**
	 * Calcula la cantidad de alumnos con un estado específico.
	 *
	 * @param estado Estado por el cual filtrar los alumnos.
	 * @return Un {@link Mono} que emite la cantidad de alumnos con el estado
	 *         proporcionado.
	 */
	public Mono<Long> countByEstado(Estado estado) {
		log.info("Calculando cantidad de alumnos con estado: {}", estado);
		return redisOperations.opsForHash().values(KEY).cast(Alumno.class)
				.filter(alumno -> estado.equals(alumno.getEstado())).count();
	}

	/**
	 * Valida si existe un alumno por su ID.
	 *
	 * @param id El ID del alumno a verificar.
	 * @return Una operación Mono<Boolean> que indica si el alumno existe o no.
	 */
	public Mono<Boolean> existsById(Long id) {
		log.info("Validando que exista alumno con id: {}", id);
		return redisOperations.opsForHash().hasKey(KEY, id.toString());
	}
}
