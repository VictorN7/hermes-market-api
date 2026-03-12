# 🛒 Hermes Market API

Hermes Market API is a RESTful backend application built with Java and Spring Boot that simulates a marketplace system.

The project emphasizes clean architecture, separation of concerns, domain-driven modeling, and scalable design instead of a simple CRUD-oriented approach.

This project serves as an architectural playground to explore scalable backend patterns and enterprise-ready design principles.

---

## 🎯 Project Purpose

**The goal of this project is to design a robust and scalable backend architecture that supports:**

- User management with address handling
- Product organization by category and brand
- Promotion system with multiple discount types
- Structured order management with delivery address tracking
- Dynamic and combinable filters
- Sustainable domain evolution

**The main focus is not just CRUD operations, but:**

- Consistent domain modeling
- Low coupling
- Clear responsibility separation
- Architecture prepared for growth

---

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
│       └─ exception
│
└─ config
```

---

## 🔎 Layer Responsibilities

### 🔹 Domain
- JPA entities
- Enums
- Core business rules and validations
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
- Global exception handling via `@ControllerAdvice`
- Input/output mapping via DTOs

---

## 🧱 Domain Model

### Core Entities

| Entity | Description |
|--------|-------------|
| User | System user with role and status |
| Address | User address, referenced by orders |
| Category | Product category |
| Brand | Product brand |
| Product | Market product with stock and status |
| Promotion | Promotional campaign with discount types |
| Order | Customer order with items and delivery info |
| OrderItem | Individual item within an order |

### Main Relationships

- A `Product` belongs to a `Category` and a `Brand`
- A `Product` can have multiple `Promotions` (ManyToMany)
- A `User` has multiple `Addresses` (OneToMany)
- An `Order` belongs to a `User`
- An `Order` references an `Address` for delivery tracking
- An `Order` contains multiple `OrderItems`
- Each `OrderItem` references a `Product`

---

## 🚀 Key Technical Highlights

- Clear separation between layers
- DTO-based API — entities are never exposed directly
- Dynamic filtering using Spring Data JPA Specification with Join
- Summary and detailed response strategies per endpoint
- Active promotion filter applied at the mapper level
- Domain-rich entities with business rule enforcement
- Global exception handling with custom exception types
- Clean and scalable code structure

---

## 🏷️ Promotion System

The API supports two promotion types:

| Type | Description |
|------|-------------|
| `DIRECT_PRICE` | Fixed promotional price applied directly to the product |
| `QUANTITY_DISCOUNT` | Discount triggered from a minimum quantity |

Business rules enforced in the domain:
- Promotional price must be lower than the original price
- Promotion period must be valid (end date after start date)
- A product cannot have two active promotions simultaneously

---

## 🛡️ Exception Handling

Global exception handling via `@ControllerAdvice` with a standardized `StandardError` response:

| Status | Exception | Description |
|--------|-----------|-------------|
| 400 | `IllegalArgumentException` | Invalid argument |
| 404 | `ResourceNotFoundException` | Resource not found |
| 409 | `BusinessException` | Business rule violation |
| 409 | `DataIntegrityViolationException` | Data integrity violation |
| 500 | `Exception` | Unexpected internal error |

---

## 🔍 Dynamic Filtering

Products support optional and combinable query parameters:

```
GET /api/v1/products?brandId=2&categoryId=1&name=lar&onSale=true
```

Filters available:

| Parameter | Type | Description |
|-----------|------|-------------|
| `categoryId` | Long | Filter by category |
| `brandId` | Long | Filter by brand |
| `name` | String | Partial name search (case-insensitive) |
| `onSale` | Boolean | Filter products with active promotions |

Implemented via **Spring Data JPA Specification with Join** — no manual queries.

---

## 🌐 Available Endpoints

### Users

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/users` | List all users |
| GET | `/api/v1/users/{id}` | Get user by id |
| GET | `/api/v1/users/{id}/orders` | List orders by user |
| GET | `/api/v1/users/{id}/addresses` | List addresses by user |

### Categories

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/categories` | List all categories |
| GET | `/api/v1/categories/{id}` | Get category by id |

### Brands

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/brands` | List all brands |
| GET | `/api/v1/brands/{id}` | Get brand by id |

### Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/products` | List products with optional filters |
| GET | `/api/v1/products/{id}` | Get product by id |

### Orders

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/orders` | List all orders |
| GET | `/api/v1/orders/{id}` | Get order by id |

### Promotions

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/promotions` | List all promotions |
| GET | `/api/v1/promotions/{id}` | Get promotion by id |

---

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
- SLF4J

---

## 🛠️ Environment

- H2 in-memory database (development)
- H2 console enabled at `/h2-console`
- Prepared for future PostgreSQL migration

Initial seed data is configured via `TestConfig` for easy endpoint testing.

---

## 🚧 Current Status

**Completed:**
- Full read layer (GET endpoints for all entities)
- Domain-rich entities with business rule enforcement
- Dynamic filtering with JPA Specification
- Promotion system with `DIRECT_PRICE` and `QUANTITY_DISCOUNT` types
- Address management linked to users and orders
- Global exception handling with custom exceptions
- DTO-based responses with active promotion filtering

**Planned next steps:**
- Write endpoints (POST, PUT, PATCH, DELETE)
- Bean Validation on request DTOs
- Migration to PostgreSQL
- Pagination and sorting
- Spring Security + JWT authentication
- Swagger / OpenAPI documentation
- Database versioning with Flyway
- Automated testing

---

## ▶️ How to Run

### 1️⃣ Clone the repository

```bash
git clone https://github.com/VictorN7/hermes-market-api.git
cd hermes-market-api
```

### 2️⃣ Run the application

```bash
./mvnw spring-boot:run
```

**Application runs at:**
```
http://localhost:8080
```

**H2 Console:**
```
http://localhost:8080/h2-console
```

---

## 👤 Author

**Victor Nogueira**

Backend Developer | Java & Spring Boot

[![LinkedIn](https://img.shields.io/badge/LinkedIn-victor--nogueira--ti-blue)](https://www.linkedin.com/in/victor-nogueira-ti/)
[![GitHub](https://img.shields.io/badge/GitHub-VictorN7-black)](https://github.com/VictorN7)