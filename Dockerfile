# Etapa 1: Descargar dependencias y construir el proyecto
FROM gradle:7.3.3-jdk11 AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia solo los archivos de Gradle para descargar las dependencias
COPY build.gradle settings.gradle ./
RUN gradle build --no-daemon || return 0

# Copia el resto de los archivos
COPY . .

# Construye el proyecto
RUN gradle build --no-daemon

# Etapa 2: Crea una imagen más pequeña para ejecutar la aplicación
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia solo el JAR construido por Gradle al contenedor
COPY --from=builder /app/build/libs/*.jar app.jar

# Expone el puerto en el que se ejecuta tu aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]
