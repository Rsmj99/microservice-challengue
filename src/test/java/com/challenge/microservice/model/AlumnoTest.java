package com.challenge.microservice.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlumnoTest {

	@Test
	void testConstructorAndGetters() {
		Alumno alumno = new Alumno(1L, "Juan", "Perez", Estado.ACTIVO, 25);

		assertThat(alumno.getId()).isEqualTo(1L);
		assertThat(alumno.getNombre()).isEqualTo("Juan");
		assertThat(alumno.getApellido()).isEqualTo("Perez");
		assertThat(alumno.getEstado()).isEqualTo(Estado.ACTIVO);
		assertThat(alumno.getEdad()).isEqualTo(25);
	}

	@Test
	void testSetter() {
		Alumno alumno = new Alumno();
		alumno.setId(2L);
		alumno.setNombre("Maria");
		alumno.setApellido("Gomez");
		alumno.setEstado(Estado.INACTIVO);
		alumno.setEdad(30);

		assertThat(alumno.getId()).isEqualTo(2L);
		assertThat(alumno.getNombre()).isEqualTo("Maria");
		assertThat(alumno.getApellido()).isEqualTo("Gomez");
		assertThat(alumno.getEstado()).isEqualTo(Estado.INACTIVO);
		assertThat(alumno.getEdad()).isEqualTo(30);
	}
}
