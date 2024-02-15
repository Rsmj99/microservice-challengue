package com.challenge.microservice.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.microservice.dto.AlumnoDTO;
import com.challenge.microservice.exception.DuplicateIdException;
import com.challenge.microservice.mapper.AlumnoMapper;
import com.challenge.microservice.model.Estado;
import com.challenge.microservice.model.PaginatedResponse;
import com.challenge.microservice.repository.IAlumnoRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Implementación del servicio para gestionar operaciones relacionadas con los
 * alumnos.
 */
@Service
@Slf4j
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private IAlumnoRepository alumnoRepository;

	/**
	 * Intenta guardar un nuevo alumno.
	 *
	 * @param alumnoDTO El DTO del alumno a guardar.
	 * @return Un Mono<Void> que representa la finalización de la operación.
	 * @throws DuplicateIdException Si ya existe un alumno con el mismo ID.
	 */
	@Override
	public Mono<Void> saveAlumno(AlumnoDTO alumnoDTO) {
		log.info("Intentando guardar un nuevo alumno: {}", alumnoDTO);
		return Mono.just(alumnoDTO).map(AlumnoMapper::mapDTOToEntity)
				.flatMap(alumno -> alumnoRepository.existsById(alumno.getId())
						.flatMap(existe -> existe ? Mono.error(new DuplicateIdException(alumno.getId()))
								: alumnoRepository.save(alumno)));
	}

	/**
	 * Intenta obtener una lista paginada de alumnos activos.
	 *
	 * @param page Número de página (basado en cero) que se desea recuperar.
	 * @param size Tamaño de la página, que determina la cantidad de elementos por
	 *             página.
	 * @return Un Mono que emite una PaginatedResponse de AlumnoDTO, que incluye la
	 *         lista de alumnos activos, el número total de elementos y el número
	 *         total de páginas.
	 */
	@Override
	public Mono<PaginatedResponse<AlumnoDTO>> getAllAlumnosActivos(int page, int size) {
		log.info("Intentando obtener los alumnos con estado: ACTIVO");
		return alumnoRepository.findByEstadoPaged(Estado.ACTIVO, page, size).collectList()
				.zipWith(alumnoRepository.countByEstado(Estado.ACTIVO))
				.flatMap(tuple -> Mono.just(new PaginatedResponse<>(
						tuple.getT1().stream().map(AlumnoMapper::mapEntityToDTO).collect(Collectors.toList()),
						tuple.getT2(), (int) Math.ceil((double) tuple.getT2() / size))));
	}
}
