# Student Management System - Backend API

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-green.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![MongoDB](https://img.shields.io/badge/MongoDB-Cloud-green.svg)
![Maven](https://img.shields.io/badge/Maven-4.0.0-blue.svg)

Un backend RESTful robusto desarrollado con Spring Boot para gestionar información de estudiantes en una base de datos MongoDB en la nube.

## 🚀 Características

- **API RESTful completa** con operaciones CRUD para estudiantes
- **Validaciones exhaustivas** de datos con Bean Validation
- **Integración con MongoDB Atlas** para almacenamiento en la nube
- **Manejo global de excepciones** con respuestas estructuradas
- **Configuración de CORS** para integración frontend
- **Variables de entorno** para configuración segura
- **Documentación OpenAPI/Swagger** (integrable)
- **Logs estructurados** para debugging
- **Health checks** con Spring Actuator

## 🏗️ Arquitectura

```
src/
├── main/
│   ├── java/edu/eci/arsw/client_spring_rest/
│   │   ├── ClientSpringRestApplication.java     # Clase principal
│   │   ├── config/
│   │   │   └── WebConfig.java                   # Configuración CORS y .env
│   │   ├── controller/
│   │   │   └── StudentController.java           # Endpoints REST
│   │   ├── dto/
│   │   │   ├── ApiResponse.java                 # Respuesta estandarizada
│   │   │   ├── StudentCreateRequest.java        # DTO para crear estudiante
│   │   │   └── StudentResponse.java             # DTO para respuesta
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java      # Manejo global de errores
│   │   │   ├── DuplicateEmailException.java     # Excepción email duplicado
│   │   │   └── StudentNotFoundException.java    # Excepción estudiante no encontrado
│   │   ├── model/
│   │   │   └── Student.java                     # Entidad MongoDB
│   │   ├── repository/
│   │   │   └── StudentRepository.java           # Repositorio MongoDB
│   │   └── service/
│   │       ├── StudentService.java              # Lógica de negocio
│   │       └── StudentServiceImpl.java          # Implementación del servicio
│   └── resources/
│       └── application.properties               # Configuración de la aplicación
└── test/
    └── java/edu/eci/arsw/client_spring_rest/
        └── ClientSpringRestApplicationTests.java
```

## 📋 Prerrequisitos

- **Java 17** o superior
- **Maven 3.6+** para gestión de dependencias
- **MongoDB Atlas** (cuenta en la nube)
- **Puerto 8080** disponible para el servidor

## 🛠️ Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone https://github.com/AnderssonProgramming/client-spring-rest.git
cd client-spring-rest
```

### 2. Configurar variables de entorno
Crear un archivo `.env` en la raíz del proyecto:

```env
# MongoDB Configuration
MONGODB_URI=mongodb+srv://username:password@cluster.mongodb.net/database_name
MONGODB_DATABASE=student_management

# Server Configuration
SERVER_PORT=8080

# CORS Configuration
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://127.0.0.1:3000

# Spring Profile
SPRING_PROFILES_ACTIVE=dev
```

### 3. Instalar dependencias
```bash
mvn clean install
```

### 4. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 🔗 Endpoints de la API

### Estudiantes

| Método | Endpoint | Descripción | Cuerpo de la Petición |
|--------|----------|-------------|----------------------|
| `GET` | `/api/students` | Obtener todos los estudiantes | - |
| `GET` | `/api/students/{id}` | Obtener estudiante por ID | - |
| `GET` | `/api/students/email/{email}` | Obtener estudiante por email | - |
| `GET` | `/api/students/program/{program}` | Obtener estudiantes por programa | - |
| `GET` | `/api/students/search?name={name}` | Buscar estudiantes por nombre | - |
| `GET` | `/api/students/birthdate-range?startDate={date}&endDate={date}` | Estudiantes por rango de fechas | - |
| `GET` | `/api/students/count/program/{program}` | Contar estudiantes por programa | - |
| `GET` | `/api/students/ordered-by-name` | Estudiantes ordenados por nombre | - |
| `POST` | `/api/students` | Crear nuevo estudiante | [StudentCreateRequest](#studentcreaterequest) |
| `PUT` | `/api/students/{id}` | Actualizar estudiante | [StudentCreateRequest](#studentcreaterequest) |
| `DELETE` | `/api/students/{id}` | Eliminar estudiante | - |

### Health Check

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/students/health` | Estado del servicio |
| `GET` | `/actuator/health` | Health check de Spring Actuator |

## 📝 Esquemas de Datos

### StudentCreateRequest
```json
{
  "name": "string (requerido, min: 2 caracteres)",
  "email": "string (requerido, formato email válido)",
  "birthDate": "date (requerido, fecha en el pasado)",
  "program": "string (requerido)"
}
```

### StudentResponse
```json
{
  "id": "string",
  "name": "string",
  "email": "string",
  "birthDate": "date",
  "program": "string",
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
```

### ApiResponse
```json
{
  "success": "boolean",
  "message": "string",
  "data": "object|array|null",
  "timestamp": "datetime"
}
```

## 🧪 Ejemplos de Uso

### Crear un estudiante
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Juan Pérez",
    "email": "juan.perez@example.com",
    "birthDate": "1995-05-15",
    "program": "Computer Science"
  }'
```

### Obtener todos los estudiantes
```bash
curl -X GET http://localhost:8080/api/students
```

### Buscar estudiantes por nombre
```bash
curl -X GET "http://localhost:8080/api/students/search?name=Juan"
```

## 🔧 Configuración de Desarrollo

### Profiles de Spring
- **dev**: Desarrollo local con logs detallados
- **prod**: Producción con configuración optimizada

### Configuración de MongoDB
```properties
# Local MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/student_management

# MongoDB Atlas (Nube)
spring.data.mongodb.uri=mongodb+srv://user:pass@cluster.mongodb.net/database
```

### Configuración de CORS
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

## 🧪 Testing

### Ejecutar tests
```bash
mvn test
```

### Tests incluidos
- **Tests de integración** para la aplicación Spring Boot
- **Tests de repositorio** con Testcontainers MongoDB
- **Tests de controlador** con MockMvc

## 📦 Construcción para Producción

### Crear JAR ejecutable
```bash
mvn clean package
```

### Ejecutar JAR
```bash
java -jar target/client-spring-rest-0.0.1-SNAPSHOT.jar
```

## 🐳 Docker (Opcional)

### Dockerfile
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/client-spring-rest-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Construir imagen
```bash
docker build -t student-management-api .
```

### Ejecutar contenedor
```bash
docker run -p 8080:8080 --env-file .env student-management-api
```

## 🔒 Seguridad

### Variables de Entorno
- **Nunca** commitear el archivo `.env`
- Usar variables de entorno en producción
- Rotar credenciales regularmente

### Validaciones
- Validación de entrada con Bean Validation
- Sanitización de datos
- Manejo seguro de excepciones

## 📊 Monitoreo

### Spring Actuator Endpoints
- `/actuator/health` - Estado de la aplicación
- `/actuator/info` - Información de la aplicación
- `/actuator/metrics` - Métricas de rendimiento

### Logs
```properties
# Configuración de logs
logging.level.edu.eci.arsw.client_spring_rest=DEBUG
logging.level.org.springframework.data.mongodb=DEBUG
```

## 🚨 Solución de Problemas

### Error de conexión a MongoDB
1. Verificar la cadena de conexión en `.env`
2. Verificar conectividad de red
3. Verificar credenciales de MongoDB Atlas

### Error de puerto ocupado
```bash
# Encontrar proceso usando puerto 8080
lsof -i :8080

# Cambiar puerto en .env
SERVER_PORT=8081
```

### Error de CORS
1. Verificar configuración en `WebConfig.java`
2. Verificar `CORS_ALLOWED_ORIGINS` en `.env`

## 🤝 Contribución

1. Fork el proyecto
2. Crear una rama para la nueva funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Commit los cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

## 📚 Documentación Adicional

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)
- [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)
- [Bean Validation](https://beanvalidation.org/)
