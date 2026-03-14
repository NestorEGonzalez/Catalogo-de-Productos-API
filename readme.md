![Maven Build](https://github.com/NestorEGonzalez/Catalogo-de-Productos-API/actions/workflows/maven.yml/badge.svg)

# 📦 API Catálogo de Productos

API REST desarrollada en **Java 17 con Spring Boot**, que implementa un catálogo de productos con enfoque en **arquitectura limpia, TDD y reproducibilidad de tests con Testcontainers**.  
Forma parte de mi portfolio y refleja buenas prácticas de desarrollo, documentación y despliegue en contenedores.

---

## 🚀 Tecnologías utilizadas
- Java 17
- Spring Boot 3.2.2
- JPA/Hibernate
- PostgreSQL
- Testcontainers (Docker)
- Maven
- OpenAPI (documentación)

---

## 📂 Arquitectura
Separación clara por capas:
- **Entity** → Modelos y anotaciones JPA.
- **Repository** → Acceso a datos con Spring Data JPA.
- **Service** → Lógica de negocio y validaciones.
- **Controller** → Endpoints REST.
- **Exception** → Manejo centralizado de errores.

---

## 🧪 Testing
- Pruebas unitarias de repositorios, servicios y controladores.
- Pruebas de integración con **Testcontainers** (PostgreSQL).
- Desarrollo guiado por pruebas (**TDD**).
- Validación de endpoints REST con **MockMvc**.

---

## 📌 Funcionalidades actuales
- Autenticación y autorización con **JWT**.
- Usuario administrador creado por defecto al iniciar la aplicación.
- Endpoints protegidos para operaciones de modificación.
- Documentación con **OpenAPI** (`openapi.yaml`).
- Despliegue completo en contenedores Docker (API + PostgreSQL).

---

## 🎯 Objetivo
Construir un catálogo de productos mantenible y escalable, base para futuros sistemas de gestión de stock.

---

⚠️ **Estado actual**: Proyecto finalizado y disponible para pruebas en Docker.
