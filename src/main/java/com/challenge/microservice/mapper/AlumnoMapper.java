package com.challenge.microservice.mapper;

import com.challenge.microservice.dto.AlumnoDTO;
import com.challenge.microservice.model.Alumno;

/**
 * Clase de utilidad para mapear entre entidades Alumno y DTOs AlumnoDTO.
 */
public class AlumnoMapper {

	private AlumnoMapper() {
	}

	/**
	 * Convierte un objeto AlumnoDTO a un objeto Alumno.
	 *
	 * @param alumnoDTO El objeto AlumnoDTO a convertir.
	 * @return El objeto Alumno resultante.
	 */
	public static Alumno mapDTOToEntity(AlumnoDTO alumnoDTO) {
		return new Alumno(alumnoDTO.getId(), alumnoDTO.getNombre(), alumnoDTO.getApellido(), alumnoDTO.getEstado(),
				alumnoDTO.getEdad());
	}

	/**
	 * Convierte un objeto Alumno a un objeto AlumnoDTO.
	 *
	 * @param alumno El objeto Alumno a convertir.
	 * @return El objeto AlumnoDTO resultante.
	 */
	public static AlumnoDTO mapEntityToDTO(Alumno alumno) {
		return new AlumnoDTO(alumno.getId(), alumno.getNombre(), alumno.getApellido(), alumno.getEstado(),
				alumno.getEdad());
	}
}
