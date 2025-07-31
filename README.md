# Oriontek Client Management

🎯 **Descripción**  
Oriontek Client Management es una API REST para gestionar clientes y sus direcciones, usando arquitectura CQRS con Spring Boot y PostgreSQL. Ideal para manejar datos con separación clara entre comandos y consultas.

---

🚀 **Stack Tecnológico**  
- Java 17 + Spring Boot  
- PostgreSQL 15 (Dockerizado)  
- Maven  
- Mockito + JUnit 5 (testing)  
- Docker & Docker Compose
- Swagger/OpenAPI (documentación automática)

---

📦 **Arquitectura y Flujo**  
- CQRS: separación clara entre comandos (modificación) y consultas (lectura).  
- DTOs para encapsular datos de entrada/salida.  
- Controladores REST para exponer la API.  
- Manejo de errores con ControllerAdvice.
- Documentación automática vía Swagger UI (`/swagger-ui.html`).
- dockerización completa de la aplicación(pendiente)

---

⚙️ **Setup Rápido con Docker**  

1. Crea un archivo `.env` basado en `.env.template` y pon tus credenciales:

```env
POSTGRES_USER=tu_usuario
POSTGRES_PASSWORD=tu_contraseña
POSTGRES_DB=tu_basededatos
```

2. Levantar la base de datos:

```bash
docker-compose up -d
```


3. Genera el jar y corre la app:
   
</code></pre>

Esto lo mostrará como:

```bash
./mvnw clean package
java -jar target/oriontek-client-management.jar
```

Endpoints principales

POST /clients - Crear cliente

PUT /clients/{id} - Editar cliente

DELETE /clients/{id} - Eliminar cliente

GET /clients - Listar clientes

GET /clients/{id} - Detalle cliente
