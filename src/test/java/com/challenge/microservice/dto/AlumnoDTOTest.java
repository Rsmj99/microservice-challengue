package com.challenge.microservice.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.microservice.model.Estado;

@SpringBootTest
class AlumnoDTOTest {

	@InjectMocks
	private AlumnoDTO alumnoDTO;

	@Test
	void testValidation() {
		AlumnoDTO alumnoDTO = new AlumnoDTO(1L, "", "Muguruza", Estado.ACTIVO, 23);

		Set<ConstraintViolation<AlumnoDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator()
				.validate(alumnoDTO);

		assertFalse(violations.isEmpty());
	}

	@Test
	void getSetId() {
		this.alumnoDTO.setId(1L);
		assertEquals(1, this.alumnoDTO.getId());
	}

	@Test
	void getSetNombre() {
		this.alumnoDTO.setNombre("Roger");
		assertEquals("Roger", this.alumnoDTO.getNombre());
	}

	@Test
	void getSetApellido() {
		this.alumnoDTO.setApellido("Muguruza");
		assertEquals("Muguruza", this.alumnoDTO.getApellido());
	}

	@Test
	void getSetEstado() {
		this.alumnoDTO.setEstado(Estado.ACTIVO);
		assertEquals(Estado.ACTIVO, this.alumnoDTO.getEstado());
	}

	@Test
	void getSetEdad() {
		this.alumnoDTO.setEdad(23);
		assertEquals(23, this.alumnoDTO.getEdad());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void equalsAndHashCodeTest() {
		AlumnoDTO alumnoDTO1 = new AlumnoDTO(1L, "John", "Doe", Estado.ACTIVO, 25);
		AlumnoDTO alumnoDTO2 = new AlumnoDTO(1L, "John", "Doe", Estado.ACTIVO, 25);
		AlumnoDTO alumnoDTO3 = new AlumnoDTO(2L, "Jane", "Doe", Estado.ACTIVO, 30);

		assertTrue(alumnoDTO1.equals(alumnoDTO1));
		assertFalse(alumnoDTO1.equals(null));
		assertFalse(alumnoDTO1.equals("alumno"));
		assertTrue(alumnoDTO1.equals(alumnoDTO2));
		assertFalse(alumnoDTO1.equals(alumnoDTO3));

		// Comprobación de hashCode
		assertEquals(alumnoDTO1.hashCode(), alumnoDTO2.hashCode());
		assertNotEquals(alumnoDTO1.hashCode(), alumnoDTO3.hashCode());
	}
}
