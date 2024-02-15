package com.challenge.microservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.microservice.dto.AlumnoDTO;
import com.challenge.microservice.exception.DuplicateIdException;
import com.challenge.microservice.model.Alumno;
import com.challenge.microservice.model.Estado;
import com.challenge.microservice.model.PaginatedResponse;
import com.challenge.microservice.repository.AlumnoRepositoryImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class AlumnoServiceTest {

	@InjectMocks
	private AlumnoServiceImpl alumnoService;

	@Mock
	private AlumnoRepositoryImpl alumnoRepositoryImpl;

	@Test
	void testSaveAlumno() {
		AlumnoDTO alumnoDTO = new AlumnoDTO(1L, "Roger", "Muguruza", Estado.ACTIVO, 23);

		when(alumnoRepositoryImpl.existsById(anyLong())).thenReturn(Mono.just(false));
		when(alumnoRepositoryImpl.save(any(Alumno.class))).thenReturn(Mono.empty());

		StepVerifier.create(alumnoService.saveAlumno(alumnoDTO)).verifyComplete();
	}

	@Test
	void testSaveAlumnoException() {
		AlumnoDTO alumnoDTO = new AlumnoDTO(1L, "Roger", "Muguruza", Estado.ACTIVO, 23);

		when(alumnoRepositoryImpl.existsById(anyLong())).thenReturn(Mono.just(true));

		StepVerifier.create(alumnoService.saveAlumno(alumnoDTO)).verifyError(DuplicateIdException.class);
	}

	@Test
	void testGetAllAlumnosActivos() {
		Alumno alumno = new Alumno(1L, "Roger", "Muguruza", Estado.ACTIVO, 23);
		AlumnoDTO alumnoDTO = new AlumnoDTO(1L, "Roger", "Muguruza", Estado.ACTIVO, 23);
		PaginatedResponse<AlumnoDTO> paginatedResponse = new PaginatedResponse<>(Arrays.asList(alumnoDTO), 1, 1);

		when(alumnoRepositoryImpl.findByEstadoPaged(Estado.ACTIVO, 0, 3))
				.thenReturn(Flux.fromIterable(Arrays.asList(alumno)));
		when(alumnoRepositoryImpl.countByEstado(Estado.ACTIVO)).thenReturn(Mono.just(1L));

		StepVerifier.create(alumnoService.getAllAlumnosActivos(0, 3)).expectNext(paginatedResponse).verifyComplete();
	}
}
