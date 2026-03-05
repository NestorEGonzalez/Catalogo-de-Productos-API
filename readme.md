![Maven Build](https://github.com/NestorEGonzalez/Catalogo-de-Productos-API/actions/workflows/maven.yml/badge.svg)

# 📦 API Catálogo de Productos

Este proyecto es una API REST desarrollada en **Java 17 con Spring Boot** que implementa un catálogo de productos.  
Forma parte de mi portfolio y está orientado a demostrar buenas prácticas de arquitectura, **TDD** y testing reproducible con **Testcontainers**.

---

## 🚀 Tecnologías utilizadas
- Java 17
- Spring Boot
- JPA/Hibernate
- PostgreSQL
- Testcontainers (Docker)
- Maven

---

## 📂 Arquitectura
El proyecto sigue una separación clara por capas:
- **Entity** → Definición de modelos y anotaciones JPA.
- **Repository** → Acceso a datos con Spring Data JPA.
- **Service** → Lógica de negocio y validaciones.
- **Controller** → Endpoints REST.
- **Exception** → Manejo centralizado de errores y mensajes consistentes.

---

## 🧪 Testing
- Pruebas unitarias de repositorios (Categoría y Producto).
- Pruebas de integración con **Testcontainers** para garantizar reproducibilidad.
- Desarrollo guiado por pruebas (**TDD**).
- Validación de endpoints REST con **MockMvc**.

---

## 📌 Funcionalidades actuales
- Configuración de Testcontainers con PostgreSQL.
- Pruebas unitarias de repositorios.
- Pruebas unitarias e integración de servicios (Categoría y Producto).
- Pruebas unitarias e integración de controllers (Categoría y Producto).

---

## 🎯 Objetivo
Construir un catálogo de productos mantenible y escalable que servirá como base para un sistema de gestión de stock en otro proyecto.

---

## 🔮 Próximos pasos
- Seguridad con autenticación de usuario y contraseña para edición.
- Documentación con Swagger/OpenAPI.
- Despliegue en Docker.

---

⚠️ **Estado actual**: El proyecto está en desarrollo. Se irán sumando nuevas funcionalidades y mejoras.
