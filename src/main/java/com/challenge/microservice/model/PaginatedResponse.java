package com.challenge.microservice.model;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa una respuesta paginada.
 *
 * @param <T> Tipo de contenido en la respuesta.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponse<T> {

	/**
	 * Lista de elementos en la página actual.
	 */
	private List<T> content;

	/**
	 * Número total de elementos sin paginar.
	 */
	private long totalElements;

	/**
	 * Número total de páginas disponibles.
	 */
	private int totalPages;

	/**
	 * Indica si algún otro objeto es "igual a" este. Dos objetos se consideran
	 * iguales si tienen los mismos valores para los campos 'content',
	 * 'totalElements' y 'totalPages'.
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
		PaginatedResponse<?> other = (PaginatedResponse<?>) obj;
		return Objects.equals(content, other.content) && totalElements == other.totalElements
				&& totalPages == other.totalPages;
	}

	/**
	 * Devuelve un valor de código hash para el objeto. El código hash se calcula en
	 * función de los valores de los campos 'content', 'totalElements' y
	 * 'totalPages'.
	 *
	 * @return Un valor de código hash para el objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(content, totalElements, totalPages);
	}

}
