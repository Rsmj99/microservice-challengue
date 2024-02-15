package com.challenge.microservice.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.challenge.microservice.model.Estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) que representa la información de un alumno.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO {

	/**
	 * Identificador único del alumno.
	 */
	@NotNull
	@Positive
	private Long id;

	/**
	 * Nombre del alumno.
	 */
	@NotBlank(message = "El campo nombre debe contener algún caracter")
	private String nombre;

	/**
	 * Apellido del alumno.
	 */
	@NotBlank(message = "El campo apellido debe contener algún caracter")
	private String apellido;

	/**
	 * Estado del alumno (por ejemplo, ACTIVO, INACTIVO).
	 */
	@NotNull
	private Estado estado;

	/**
	 * Edad del alumno.
	 */
	@PositiveOrZero
	private int edad;

	/**
	 * Devuelve un valor de código hash para el objeto. El código hash se calcula en
	 * función de los valores de los campos 'apellido', 'edad', 'estado', 'id' y
	 * 'nombre'.
	 *
	 * @return Un valor de código hash para el objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(apellido, edad, estado, id, nombre);
	}

	/**
	 * Indica si algún otro objeto es "igual a" este. Dos objetos se consideran
	 * iguales si tienen los mismos valores para los campos 'apellido', 'edad',
	 * 'estado', 'id' y 'nombre'.
	 *
	 * @param obj El objeto de referencia con el que se va a comparar.
	 * @return {@code true} si este objeto es igual al argumento 'obj';
	 *         {@code false} en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlumnoDTO other = (AlumnoDTO) obj;
		return Objects.equals(apellido, other.apellido) && edad == other.edad && estado == other.estado
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

}
