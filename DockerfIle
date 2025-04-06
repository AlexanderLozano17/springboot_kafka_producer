# Dockerfile para la aplicacion Spring Boot modular

# Etapa 1: Build
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR del modulo principal al contenedor y lo renombra
COPY --from=build /app/main-app/target/main-app-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8081 para la aplicación
EXPOSE 8081

# Comando de inicio para ejecutar la aplicacion Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
