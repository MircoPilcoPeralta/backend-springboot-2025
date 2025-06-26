# Usa una imagen oficial de Java 17
FROM eclipse-temurin:17-jdk-alpine

# Crea un directorio para la app
WORKDIR /app

# Copia el JAR de tu aplicación al contenedor
COPY target/demo1Springboot-0.0.1-SNAPSHOT.jar app.jar

# Define la variable de entorno (opcional por ahora)
ENV DB_URL="jdbc:postgresql://ep-sweet-waterfall-a8vp74sb-pooler.eastus2.azure.neon.tech/company"

# Expone el puerto (si usas el 8080 por defecto)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]