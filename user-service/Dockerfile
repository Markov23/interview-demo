# Imagen base con JDK 17
FROM openjdk:21-jdk-slim

# Directorio de la aplicación dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado por Spring Boot al contenedor
COPY target/*.jar app.jar

# Expone el puerto que usa Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","app.jar"]