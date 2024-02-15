package com.challenge.microservice.controller;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.challenge.microservice.dto.AlumnoDTO;
import com.challenge.microservice.model.Estado;

import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlumnoControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	@Order(1)
	void testSaveAlumno() {
		AlumnoDTO alumnoDTO = new AlumnoDTO(100L, "Roger", "Muguruza", Estado.ACTIVO, 23);

		webTestClient.post().uri("/alumnos").contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(alumnoDTO), AlumnoDTO.class).exchange().expectStatus().isCreated();
	}

	@Test
	@Order(2)
	void testGetAllAlumnosActivos() {
		webTestClient.get().uri("/alumnos/activos").exchange().expectStatus().isOk().expectBody()
				.jsonPath("$.totalElements").value(greaterThanOrEqualTo(1)).jsonPath("$.totalPages")
				.value(greaterThanOrEqualTo(1));
	}
}
