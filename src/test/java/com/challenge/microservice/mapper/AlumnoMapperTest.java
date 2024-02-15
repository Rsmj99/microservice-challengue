package com.challenge.microservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.microservice.dto.AlumnoDTO;
import com.challenge.microservice.model.Alumno;
import com.challenge.microservice.model.Estado;

@SpringBootTest
class AlumnoMapperTest {

	@Test
	void testMapDTOToEntity() {
		// Arrange
		AlumnoDTO alumnoDTO = new AlumnoDTO(1L, "Roger", "Muguruza", Estado.ACTIVO, 24);

		// Act
		Alumno result = AlumnoMapper.mapDTOToEntity(alumnoDTO);

		// Assert
		assertEquals(alumnoDTO.getId(), result.getId());
		assertEquals(alumnoDTO.getNombre(), result.getNombre());
		assertEquals(alumnoDTO.getApellido(), result.getApellido());
		assertEquals(alumnoDTO.getEstado(), result.getEstado());
		assertEquals(alumnoDTO.getEdad(), result.getEdad());
	}

	@Test
	void testMapEntityToDTO() {
		// Arrange
		Alumno alumno = new Alumno(1L, "Roger", "Muguruza", Estado.ACTIVO, 24);

		// Act
		AlumnoDTO result = AlumnoMapper.mapEntityToDTO(alumno);

		// Assert
		assertEquals(alumno.getId(), result.getId());
		assertEquals(alumno.getNombre(), result.getNombre());
		assertEquals(alumno.getApellido(), result.getApellido());
		assertEquals(alumno.getEstado(), result.getEstado());
		assertEquals(alumno.getEdad(), result.getEdad());
	}
}
