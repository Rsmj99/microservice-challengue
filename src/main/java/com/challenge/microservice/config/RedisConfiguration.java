package com.challenge.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.challenge.microservice.model.Alumno;

/**
 * Configuración de Redis para la aplicación.
 */
@Configuration
public class RedisConfiguration {

	/**
	 * Configuración para proporcionar operaciones reactivas de Redis con Alumno
	 * como el tipo de valor.
	 * 
	 * @param factory La fábrica de conexión reactiva de Redis.
	 * @return Operaciones reactivas de Redis para la entidad Alumno.
	 */
	@Bean
	ReactiveRedisOperations<String, Alumno> redisOperations(ReactiveRedisConnectionFactory factory) {
		RedisSerializationContext<String, Alumno> serializationContext = RedisSerializationContext
				.<String, Alumno>newSerializationContext(new StringRedisSerializer()).key(new StringRedisSerializer())
				.value(new GenericToStringSerializer<>(Alumno.class))
				.hashKey(new Jackson2JsonRedisSerializer<>(Integer.class))
				.hashValue(new GenericJackson2JsonRedisSerializer()).build();
		return new ReactiveRedisTemplate<>(factory, serializationContext);
	}
}
