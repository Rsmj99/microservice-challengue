package com.challenge.microservice.repository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;

import com.challenge.microservice.model.Alumno;
import com.challenge.microservice.model.Estado;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class AlumnoRepositoryTest {

	@InjectMocks
	private AlumnoRepositoryImpl alumnoRepositoryImpl;

	@Mock
	private ReactiveRedisOperations<String, Alumno> redisOperations;

	@Mock
	private ReactiveHashOperations<String, Object, Object> hashOperations;

	@Test
	void testSave() {
		Alumno alumno = new Alumno(1L, "Roger", "Muguruza", Estado.ACTIVO, 23);

		when(redisOperations.opsForHash()).thenReturn(hashOperations);
		when(hashOperations.put(anyString(), anyString(), any(Alumno.class))).thenReturn(Mono.just(true));

		StepVerifier.create(alumnoRepositoryImpl.save(alumno)).verifyComplete();

		verify(redisOperations).opsForHash();
		verify(hashOperations).put(anyString(), anyString(), any(Alumno.class));
	}

	@Test
	void testfindByEstadoPaged() {
		Alumno alumno1 = new Alumno(1L, "Roger", "Muguruza", Estado.ACTIVO, 23);
		Alumno alumno2 = new Alumno(2L, "Nombre", "Apellido", Estado.INACTIVO, 30);
		Alumno alumno3 = new Alumno(3L, "Steven", "Julca", Estado.ACTIVO, 25);
		List<Alumno> alumnos = Arrays.asList(alumno1, alumno2, alumno3);

		when(redisOperations.opsForHash()).thenReturn(hashOperations);
		when(hashOperations.values(anyString())).thenReturn(Flux.fromIterable(alumnos));

		StepVerifier.create(alumnoRepositoryImpl.findByEstadoPaged(Estado.ACTIVO, 0, 3)).expectNext(alumno1, alumno3)
				.verifyComplete();

		verify(redisOperations).opsForHash();
		verify(hashOperations).values(anyString());
	}

	@Test
	void testCountByEstado() {
		Alumno alumno1 = new Alumno(1L, "Roger", "Muguruza", Estado.ACTIVO, 23);
		Alumno alumno2 = new Alumno(2L, "Nombre", "Apellido", Estado.INACTIVO, 30);
		Alumno alumno3 = new Alumno(3L, "Steven", "Julca", Estado.ACTIVO, 25);
		List<Alumno> alumnos = Arrays.asList(alumno1, alumno2, alumno3);

		when(redisOperations.opsForHash()).thenReturn(hashOperations);
		when(hashOperations.values(anyString())).thenReturn(Flux.fromIterable(alumnos));

		StepVerifier.create(alumnoRepositoryImpl.countByEstado(Estado.ACTIVO)).expectNext(2L).verifyComplete();

		verify(redisOperations).opsForHash();
		verify(hashOperations).values(anyString());
	}

	@Test
	void testExistsById() {
		when(redisOperations.opsForHash()).thenReturn(hashOperations);
		when(hashOperations.hasKey(anyString(), anyString())).thenReturn(Mono.just(true));

		StepVerifier.create(alumnoRepositoryImpl.existsById(1L)).expectNext(true).verifyComplete();

		verify(redisOperations).opsForHash();
		verify(hashOperations).hasKey(anyString(), anyString());
	}
}
