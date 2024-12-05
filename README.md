# Proyecto Spring Boot WebFlux: Gestión de Pedidos

Este proyecto es un ejemplo de una aplicación Spring Boot que implementa un sistema de gestión de pedidos utilizando MongoDB como base de datos. A continuación, se detallan los pasos necesarios para ejecutar el proyecto en otra máquina.

## **Requisitos Previos**
Asegúrate de tener instalados los siguientes programas:
-   [Java 17 JDK](https://adoptium.net/) o superior
-   [Maven](https://maven.apache.org/)
-   [Docker](https://www.docker.com/)
-   Docker Compose
### **a. Construir el JAR del proyecto**

1.  Asegúrate de que Maven esté instalado.
2.  Desde la raíz del proyecto, ejecuta:
    
    `mvn clean package` 
    
    Esto generará el archivo JAR en la carpeta `target/` con el nombre `GestionPedidos-0.0.1-SNAPSHOT.jar`.

### **b. Levantar los servicios con Docker Compose**

1.  Ejecuta el siguiente comando para construir y levantar los contenedores:
    

    `docker-compose up --build` 
    
2.  Docker Compose iniciará dos servicios:
    
    -   **MongoDB**: Accesible en `mongodb://localhost:27010`
    -   **Aplicación Spring Boot**: Accesible en `http://localhost:8080`
3.  Para verificar que los contenedores están corriendo:
    
    `docker ps` 

### **c. Endpoints disponibles**

 ### Clientes: 
`GET http://localhost:8080/api/clientes`
`GET http://localhost:8080/api/clientes/{id}`
`POST http://localhost:8080/api/clientes`
`PUT http://localhost:8080/api/clientes/{id}`
`DELETE http://localhost:8080/api/clientes/{id}`
 ### Productos: 
`GET http://localhost:8080/api/productos`
`GET http://localhost:8080/api/productos/{id}`
`POST http://localhost:8080/api/productos`
`PUT http://localhost:8080/api/productos/{id}`
`DELETE http://localhost:8080/api/productos/{id}`
 ### Pedidos: 
`GET http://localhost:8080/api/pedidos`
`GET http://localhost:8080/api/pedidos/{id}`
`POST http://localhost:8080/api/pedidos`
`DELETE http://localhost:8080/api/pedidos/{id}`

![chems thanks](https://i.pinimg.com/1200x/98/2e/a9/982ea98481fcbdf3281e699eb424fafd.jpg)
