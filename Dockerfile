# Imagen base con JDK 17 (ajusta según tu versión de Java)
FROM openjdk:17-jdk-slim
# Directorio de trabajo dentro del contenedor
WORKDIR /app
# Copiar el JAR generado al contenedor
COPY target/GestionPedidos-0.0.1-SNAPSHOT.jar app.jar
# Puerto que expone la aplicación
EXPOSE 8080
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
