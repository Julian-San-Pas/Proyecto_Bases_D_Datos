# 🏫 Centro Comunitario API
### Sistema de Gestión de Centro Comunitario de Bienestar y Actividades Formativas

---

## 🚀 Requisitos Previos

| Herramienta | Versión mínima |
|-------------|---------------|
| Java JDK    | 17+           |
| Maven       | 3.8+          |
| MongoDB     | 6.0+          |
| Postman     | Cualquiera    |

---

## ⚙️ Configuración e Instalación

### 1. Instalar MongoDB (local)

**Windows / Mac:**
Descarga desde https://www.mongodb.com/try/download/community

**Ubuntu/Linux:**
```bash
sudo apt-get install -y mongodb
sudo systemctl start mongod
```

**O usar MongoDB con Docker:**
```bash
docker run -d -p 27017:27017 --name mongo-centro mongo:6.0
```

### 2. Clonar / Descomprimir el proyecto

```bash
cd centro-comunitario
```

### 3. Compilar y ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

La API quedará corriendo en: **http://localhost:8080**

---

## 📁 Estructura del Proyecto

```
src/main/java/com/centro/api/
├── CentroComunidadApplication.java   ← Clase principal
├── model/
│   ├── Usuario.java                  ← Entidad Usuario (4 roles)
│   ├── Actividad.java                ← Entidad Actividad
│   └── Models.java                   ← Sesión, Inscripción, Evaluación,
│                                        Programa, Anuncio, Foro
├── repository/
│   ├── UsuarioRepository.java
│   ├── ActividadRepository.java
│   ├── InscripcionRepository.java
│   ├── SesionRepository.java
│   ├── EvaluacionRepository.java
│   ├── ProgramaRepository.java
│   ├── AnuncioRepository.java
│   └── ForoRepository.java
├── service/
│   ├── UsuarioService.java
│   ├── ActividadService.java
│   └── InscripcionService.java
├── controller/
│   └── Controllers.java              ← UsuarioController, ActividadController,
│                                        InscripcionController
├── config/
│   └── SecurityConfig.java           ← CORS + Seguridad
├── exception/
│   └── GlobalExceptionHandler.java   ← Manejo global de errores
└── dto/
    └── DTOs.java                     ← Clases de transferencia de datos
```

---

## 🔗 Endpoints de la API

### 👥 Usuarios — `/api/v1/usuarios`
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST   | `/` | Registrar usuario |
| GET    | `/` | Listar todos |
| GET    | `/{id}` | Buscar por ID |
| GET    | `/rol/{rol}` | Buscar por rol |
| GET    | `/activos` | Listar activos |
| PUT    | `/{id}` | Actualizar |
| DELETE | `/{id}` | Desactivar |

**Roles disponibles:** `PARTICIPANTE`, `INSTRUCTOR`, `COORDINADOR`, `ADMINISTRADOR`

### 📚 Actividades — `/api/v1/actividades`
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST   | `/` | Crear actividad |
| GET    | `/` | Listar todas |
| GET    | `/{id}` | Buscar por ID |
| GET    | `/categoria/{cat}` | Filtrar por categoría |
| GET    | `/estado/{estado}` | Filtrar por estado |
| GET    | `/buscar?nombre=X` | Buscar por nombre |
| GET    | `/rango-fechas?inicio=&fin=` | Filtrar por fechas |
| GET    | `/{id}/cupos-disponibles` | Ver cupos |
| GET    | `/pendientes-aprobacion` | Pendientes |
| PUT    | `/{id}` | Actualizar |
| PATCH  | `/{id}/estado` | Cambiar estado |
| PATCH  | `/{id}/aprobar` | Aprobar actividad |
| DELETE | `/{id}` | Eliminar |

**Categorías:** `ARTE`, `DEPORTE`, `TECNOLOGIA`, `SALUD`, `EMPRENDIMIENTO`, `DESARROLLO_PERSONAL`  
**Estados:** `PROGRAMADA`, `EN_CURSO`, `FINALIZADA`, `CANCELADA`

### 📋 Inscripciones — `/api/v1/inscripciones`
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST   | `/` | Inscribir participante |
| GET    | `/{id}` | Ver inscripción |
| GET    | `/participante/{id}` | Historial del participante |
| GET    | `/actividad/{id}` | Participantes de actividad |
| PATCH  | `/{id}/cancelar` | Cancelar inscripción |

### 📅 Sesiones — `/api/v1/sesiones`
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST   | `/` | Crear sesión |
| GET    | `/` | Listar todas |
| GET    | `/actividad/{id}` | Sesiones de actividad |
| PATCH  | `/{id}/asistencia` | Registrar asistencia |

### ⭐ Evaluaciones — `/api/v1/evaluaciones`
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST   | `/` | Crear evaluación |
| GET    | `/actividad/{id}` | Evaluaciones de actividad |
| GET    | `/participante/{id}` | Evaluaciones de participante |

### 🎯 Programas — `/api/v1/programas`
### 📢 Anuncios — `/api/v1/anuncios`
### 💬 Foros — `/api/v1/foros`

---

## 📬 Configurar Postman

1. Abre **Postman**
2. Click en **Import**
3. Selecciona el archivo: `Centro_Comunitario_API.postman_collection.json`
4. La colección aparecerá con todas las peticiones organizadas
5. Las variables `{{base_url}}`, `{{token}}`, `{{usuario_id}}`, `{{actividad_id}}`, etc. se llenan automáticamente con los scripts de test

### Flujo recomendado para probar:
```
1. 👥 Usuarios → Registrar Usuario (participante)
2. 👥 Usuarios → Registrar Instructor
3. 📚 Actividades → Crear actividad - Arte
4. 📋 Inscripciones → Inscribir participante en actividad
5. 📅 Sesiones → Crear sesión presencial
6. 📅 Sesiones → Registrar asistencia
7. ⭐ Evaluaciones → Crear evaluación de participante
8. 💬 Foros → Crear foro / Agregar comentario
```

---

## 🔧 application.properties clave

```properties
# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/centro_comunitario

# Puerto
server.port=8080

# Zona horaria (Colombia)
spring.jackson.time-zone=America/Bogota
```

---

## 🐞 Errores comunes

| Error | Causa | Solución |
|-------|-------|----------|
| `Connection refused 27017` | MongoDB no está corriendo | Iniciar MongoDB |
| `Duplicate key` | Correo/documento ya existe | Usar datos únicos |
| `Validation failed` | Campos requeridos vacíos | Revisar body del request |
| `404 Not Found` | ID incorrecto | Verificar el ID en las variables |

---

## 📌 Notas de Desarrollo

- Los IDs de MongoDB se generan automáticamente (ObjectId como String)
- La paginación se puede agregar con `Pageable` en los repositories
- Para producción, configurar variables de entorno para credenciales
- El módulo de seguridad JWT está preparado pero en modo permisivo para desarrollo
