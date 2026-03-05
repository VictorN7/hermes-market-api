# 🛒 Hermes Market API

Hermes Market API is a RESTful backend application built with Java and Spring Boot that simulates a marketplace system.

The project emphasizes clean architecture, separation of concerns, domain-driven modeling, and scalable design instead of a simple CRUD-oriented approach.

This project serves as an architectural playground to explore scalable backend patterns and enterprise-ready design principles.

## 🎯 Project Purpose

**The goal of this project is to design a robust and scalable backend architecture that supports:**

- User management
- Product organization by category and brand
- Structured order queries
- Dynamic and combinable filters
- Sustainable domain evolution

**The main focus is not just CRUD operations, but:**

- Consistent domain modeling
- Low coupling
- Clear responsibility separation
- Architecture prepared for growth


## 🧩 Architecture Overview

The project follows a layered structure inspired by Domain-Driven Design (DDD):

```text
com.hermes.market
│
├─ domain
│   ├─ user
│   ├─ product
│   └─ order
│
├─ application
│   ├─ dto
│   │   ├─ request
│   │   ├─ response
│   │   └─ filter
│   ├─ service
│   ├─ mapper
│   └─ exception
│
├─ infrastructure
│   └─ repository
│       └─ specification
│
├─ web
│   └─ controller
│
└─ config
```

## 🔎 Layer Responsibilities

### 🔹 Domain

- JPA entities
- Enums
- Core business rules
- Entity relationships

### 🔹 Application

- Application services
- DTOs (Request / Response / Filter)
- Mappers
- Custom exceptions

### 🔹 Infrastructure

- JPA repositories
- Dynamic filtering with Specification API
- Database integration

### 🔹 Web

- REST controllers
- HTTP exposure layer
- Input/output mapping via DTOs


## 🧱 Domain Model

### Core Entities

- User
- Category
- Brand
- Product
- Order
- OrderItem

### Main Relationships

- A Product belongs to a Category
- A Product belongs to a Brand
- An Order belongs to a User
- An Order contains multiple OrderItems
- Each OrderItem references a Product

Relationships are mapped using JPA best practices, ensuring domain consistency and proper association management.



## 🚀 Key Technical Highlights

- Clear separation between layers
- DTO-based API (entities are never exposed directly)
- Dynamic filtering using Spring Data JPA Specification
- Summary and detailed response strategies
- Clean and scalable code structure
- Domain-focused modeling


## 🔍 Dynamic Filtering Example

Products support optional and combinable query parameters:
```
GET /api/v1/products?brandId=2&categoryId=1&name=lar
```

## 🌐 Available Endpoints (Read Layer)

### Users

`GET /api/v1/users`
`GET /api/v1/users/{id}`

### Categories

`GET /api/v1/categories`
`GET /api/v1/categories/{id}`

### Brands

`GET /api/v1/brands`
`GET /api/v1/brands/{id}`

### Products

`GET /api/v1/products`
`GET /api/v1/products/{id}`

### Orders

`GET /api/v1/orders`
`GET /api/v1/orders/{id}`

Orders include:

- User information
- Item list
- Product reference per item
- Calculated total value


## ⚙️ Technologies

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database (development)
- Maven
- Lombok
- Bean Validation
- Jackson


## 🛠️ Environment

- H2 in-memory database (development)
- H2 console enabled
- Prepared for future PostgreSQL migration

Initial seed data is configured for easy endpoint testing


## 🚧 Current Status

**The project is currently focused on:**

- Read layer consolidation  
- DTO refinement  
- Architectural improvements  

**Planned next steps:**

- Write endpoints (POST, PUT, PATCH, DELETE)  
- Authentication and authorization  
- Pagination and sorting  
- Database versioning (Flyway)  
- Swagger documentation  
- Automated testing  

## ▶️ How to Run

### 1️⃣ Clone the repository

```bash
git clone https://github.com/VictorN7/hermes-market-api.git
cd hermes-market-api
```

**Application runs at:**

```bash
http://localhost:8080
```

**H2 Console:**

```bash
http://localhost:8080/h2-console
```
## 👤 Author

Victor Nogueira

Backend Developer | Java & Spring Boot


