package com.challenge.microservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.microservice.dto.AlumnoDTO;

@SpringBootTest
class PaginatedResponseTest {

	@InjectMocks
	private PaginatedResponse<AlumnoDTO> paginatedResponse;

	@Test
	void getSetContent() {
		AlumnoDTO alumnoDTO = new AlumnoDTO(1L, "", "Muguruza", Estado.ACTIVO, 23);
		this.paginatedResponse.setContent(Arrays.asList(alumnoDTO));
		assertEquals(Arrays.asList(alumnoDTO), this.paginatedResponse.getContent());
	}

	@Test
	void getSetNombre() {
		this.paginatedResponse.setTotalElements(5);
		assertEquals(5, this.paginatedResponse.getTotalElements());
	}

	@Test
	void getSetApellido() {
		this.paginatedResponse.setTotalPages(3);
		assertEquals(3, this.paginatedResponse.getTotalPages());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void equalsAndHashCodeTest() {
		AlumnoDTO alumnoDTO = new AlumnoDTO(1L, "", "Muguruza", Estado.ACTIVO, 23);
		PaginatedResponse<AlumnoDTO> paginatedResponse1 = new PaginatedResponse<>(Arrays.asList(alumnoDTO), 1, 3);
		PaginatedResponse<AlumnoDTO> paginatedResponse2 = new PaginatedResponse<>(Arrays.asList(alumnoDTO), 1, 3);
		AlumnoDTO alumnoDTO2 = new AlumnoDTO(1L, "John", "Doe", Estado.ACTIVO, 25);
		PaginatedResponse<AlumnoDTO> paginatedResponse3 = new PaginatedResponse<>(Arrays.asList(alumnoDTO2), 1, 3);

		assertTrue(paginatedResponse1.equals(paginatedResponse1));
		assertFalse(paginatedResponse1.equals(null));
		assertFalse(paginatedResponse1.equals("alumno"));
		assertTrue(paginatedResponse1.equals(paginatedResponse2));
		assertFalse(paginatedResponse1.equals(paginatedResponse3));

		// Comprobación de hashCode
		assertEquals(paginatedResponse1.hashCode(), paginatedResponse2.hashCode());
		assertNotEquals(paginatedResponse1.hashCode(), paginatedResponse3.hashCode());
	}
}
