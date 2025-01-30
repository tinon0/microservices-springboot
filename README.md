### 📚 Library Microservices


# Gestión de Biblioteca con Microservicios

Bienvenido al repositorio de **Library Microservices**, una solución moderna y escalable para gestionar una biblioteca utilizando **Java con Spring Boot**. Este proyecto está basado en una arquitectura de microservicios, con integración de un **API Gateway** y orquestación mediante **Docker Compose**.

## 🛠️ Descripción del Proyecto

Este sistema permite gestionar de manera eficiente los procesos clave de una biblioteca a través de microservicios independientes que interactúan entre sí. Cada servicio se encarga de una parte crítica del flujo de trabajo:

- **Servicio de Clientes**: Registra a los usuarios (personas) y expone esta información a otros servicios para su consumo.
- **Servicio de Biblioteca**: Gestiona los libros, permite alquilarlos, devolverlos y calcula los montos a facturar según el tiempo de alquiler.
- **API Gateway**: Un punto centralizado para enrutar las peticiones hacia los microservicios, optimizando la comunicación.

### 🌐 Arquitectura y Microservicios

1. **Microservicio de Clientes (MS-CLIENTES)**: Registra personas y proporciona un endpoint para obtener una persona aleatoria. ¡Perfecto para generar datos de prueba de manera sencilla!
2. **Microservicio de Biblioteca (MS-BIBLIOTECA)**: Gestiona el inventario de libros y permite realizar reservas, devoluciones, facturación y generar reportes.
3. **API Gateway (MS-API GATEWAY)**: Administra las peticiones de entrada y las enruta hacia los microservicios adecuados, simplificando la comunicación y mejorando la escalabilidad.

### 🚀 Características Destacadas

- **Escalabilidad**: Los microservicios son completamente independientes, lo que permite escalar cada componente según sea necesario.
- **Docker Compose**: Orquestación fácil de los servicios con Docker, asegurando un despliegue rápido y eficiente.
- **Pruebas Automatizadas**: Todos los servicios incluyen pruebas unitarias para garantizar la calidad y estabilidad del sistema.
- **Modularidad**: Puedes mejorar o ampliar cada microservicio de manera aislada sin afectar al resto del sistema.

---

# Management with Microservices

Welcome to the **Library Microservices** repository, a modern and scalable solution for managing a library using **Java with Spring Boot**. This project is based on a microservices architecture, with integration of an **API Gateway** and orchestration using **Docker Compose**.

## 🛠️ Project Description

This system allows efficient management of key library processes through independent microservices that interact with each other. Each service handles a critical part of the workflow:

- **Client Service**: Registers users (persons) and exposes this information to other services for consumption.
- **Library Service**: Manages books, allows them to be rented, returned, and calculates the amounts to be invoiced based on rental time.
- **API Gateway**: A centralized point for routing requests to microservices, optimizing communication.

### 🌐 Architecture and Microservices

1. **Client Microservice (MS-CLIENTS)**: Registers persons and provides an endpoint to fetch a random person. Perfect for generating test data easily!
2. **Library Microservice (MS-LIBRARY)**: Manages the book inventory, allows for reservations, returns, invoicing, and generates reports.
3. **API Gateway (MS-API GATEWAY)**: Manages incoming requests and routes them to the appropriate microservices, simplifying communication and improving scalability.

### 🚀 Key Features

- **Scalability**: Microservices are completely independent, allowing each component to be scaled as needed.
- **Docker Compose**: Easy orchestration of services with Docker, ensuring a fast and efficient deployment.
- **Automated Tests**: All services include unit tests to ensure system quality and stability.
- **Modularity**: You can improve or extend each microservice in isolation without affecting the rest of the system.
