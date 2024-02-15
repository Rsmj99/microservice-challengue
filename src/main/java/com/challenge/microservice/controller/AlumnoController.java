package com.challenge.microservice.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.challenge.microservice.dto.AlumnoDTO;
import com.challenge.microservice.model.PaginatedResponse;
import com.challenge.microservice.service.IAlumnoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Controlador para la gestión de operaciones relacionadas con los alumnos.
 */
@RestController
@RequestMapping("/alumnos")
@Validated
@Api(tags = "Alumnos")
@Slf4j
public class AlumnoController {

	@Autowired
	private IAlumnoService alumnoService;

	/**
	 * Maneja las solicitudes para guardar un nuevo alumno.
	 *
	 * @param alumnoDTO El DTO del alumno a guardar.
	 * @return Un Mono<Void> que representa la finalización de la operación.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Guardar un nuevo alumno", notes = "Guarda un nuevo alumno en el sistema.")
	public Mono<Void> saveAlumno(@Valid @RequestBody AlumnoDTO alumnoDTO) {
		log.info("Recibida solicitud para guardar un nuevo alumno: {}", alumnoDTO);
		return alumnoService.saveAlumno(alumnoDTO);
	}

	/**
	 * Obtiene una lista paginada de alumnos activos con estado 'ACTIVO'.
	 *
	 * @param page Número de página (predeterminado: 0, mínimo: 0) para la
	 *             paginación.
	 * @param size Tamaño de la página (predeterminado: 10, mínimo: 1) para la
	 *             paginación.
	 * @return Un {@code Mono} que emite una respuesta paginada de tipo
	 *         {@code PaginatedResponse<AlumnoDTO>} que contiene la lista de alumnos
	 *         activos.
	 * @throws ResponseStatusException Si los parámetros de paginación no cumplen
	 *                                 con los requisitos especificados.
	 * @see PaginatedResponse
	 * @see AlumnoDTO
	 */
	@GetMapping("/activos")
	@ApiOperation(value = "Obtener alumnos activos", notes = "Obtiene una lista paginada de alumnos con estado ACTIVO.")
	public Mono<PaginatedResponse<AlumnoDTO>> getAllAlumnosActivos(
			@RequestParam(defaultValue = "0") @Min(value = 0, message = "El parámetro 'page' debe ser mayor o igual a 0") int page,
			@RequestParam(defaultValue = "10") @Min(value = 1, message = "El parámetro 'size' debe ser mayor o igual a 1") int size) {
		log.info("Recibida solicitud para obtener alumnos por paginado con estado: ACTIVO");
		return alumnoService.getAllAlumnosActivos(page, size);
	}
}
