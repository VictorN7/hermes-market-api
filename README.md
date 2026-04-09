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

## 🔐 Authentication

The project now includes a basic authentication endpoint:

```text
POST /api/v1/auth/login
```

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

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/auth/login` | Authenticate user by email and password |

### Users

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/users` | List all active users |
| GET | `/api/v1/users/{id}` | Get active user by id |
| GET | `/api/v1/users/{id}/orders` | List orders by user |
| GET | `/api/v1/users/{id}/addresses` | List addresses by user |
| GET | `/api/v1/users/inactive` | List inactive users |
| GET | `/api/v1/users/inactive/{id}` | Get inactive user by id |
| POST | `/api/v1/users` | Create a new user |
| PUT | `/api/v1/users/{id}` | Update user basic information |
| PATCH | `/api/v1/users/{id}/password` | Change user password |
| PATCH | `/api/v1/users/{id}/activate` | Activate user |
| PATCH | `/api/v1/users/{id}/deactivate` | Deactivate user |
| PATCH | `/api/v1/users/{id}/block` | Block user |
| PATCH | `/api/v1/users/{id}/unlock` | Unlock user |
| DELETE | `/api/v1/users/{id}` | Delete user if never purchased, otherwise deactivate |

### Addresses

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/users/{id}/addresses` | Add a new address to the user |
| DELETE | `/api/v1/users/{id}/addresses/{addressId}` | Delete address only if it was never used in an order |

### Categories

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/categories` | List all active categories |
| GET | `/api/v1/categories/{id}` | Get active category by id |
| GET | `/api/v1/categories/inactive` | List inactive categories |
| GET | `/api/v1/categories/inactive/{id}` | Get inactive category by id |
| POST | `/api/v1/categories` | Create a new category |
| PATCH | `/api/v1/categories/{id}/name` | Update category name |
| PATCH | `/api/v1/categories/{id}/activate` | Activate category |
| PATCH | `/api/v1/categories/{id}/deactivate` | Deactivate category |
| DELETE | `/api/v1/categories/{id}` | Delete category if it has no products, otherwise deactivate |

### Brands

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/brands` | List all active brands |
| GET | `/api/v1/brands/{id}` | Get active brand by id |
| GET | `/api/v1/brands/inactive` | List inactive brands |
| GET | `/api/v1/brands/inactive/{id}` | Get inactive brand by id |
| POST | `/api/v1/brands` | Create a new brand |
| PATCH | `/api/v1/brands/{id}/name` | Update brand name |
| PATCH | `/api/v1/brands/{id}/activate` | Activate brand |
| PATCH | `/api/v1/brands/{id}/deactivate` | Deactivate brand |
| DELETE | `/api/v1/brands/{id}` | Delete brand if it has no products, otherwise deactivate |

### Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/products` | List products with optional filters |
| GET | `/api/v1/products/{id}` | Get product by id |
| GET | `/api/v1/products/inactive` | List inactive products |
| GET | `/api/v1/products/inactive/{id}` | Get inactive product by id |
| POST | `/api/v1/products` | Create a new product |
| PUT | `/api/v1/products/{id}` | Fully update product information |
| PATCH | `/api/v1/products/{id}/stock` | Update product stock quantity |
| PATCH | `/api/v1/products/{id}/activate` | Activate product |
| PATCH | `/api/v1/products/{id}/deactivate` | Deactivate product |
| DELETE | `/api/v1/products/{id}` | Delete product if never sold, otherwise deactivate |

### Orders

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/orders` | List all orders |
| GET | `/api/v1/orders/{id}` | Get order by id |
| POST | `/api/v1/orders` | Create a new order |
| POST | `/api/v1/orders/{id}/items` | Add item to order |
| PATCH | `/api/v1/orders/{orderId}/items/{itemId}/quantity` | Update order item quantity |
| PATCH | `/api/v1/orders/{id}/pay` | Mark order as paid |
| PATCH | `/api/v1/orders/{id}/ship` | Mark order as shipped |
| PATCH | `/api/v1/orders/{id}/deliver` | Mark order as delivered |
| PATCH | `/api/v1/orders/{id}/cancel` | Cancel order when status is CREATED |
| DELETE | `/api/v1/orders/{id}/items/{itemId}` | Remove item from order when status is CREATED |

### Promotions

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/promotions` | List all active promotions |
| GET | `/api/v1/promotions/{id}` | Get active promotion by id |
| GET | `/api/v1/promotions/inactive` | List inactive promotions |
| GET | `/api/v1/promotions/inactive/{id}` | Get inactive promotion by id |
| POST | `/api/v1/promotions` | Create a new promotion |
| POST | `/api/v1/promotions/{promotionId}/products/{productId}` | Add product to promotion |
| PATCH | `/api/v1/promotions/{id}/activate` | Activate promotion |
| PATCH | `/api/v1/promotions/{id}/deactivate` | Deactivate promotion |
| DELETE | `/api/v1/promotions/{promotionId}/products/{productId}` | Remove product from promotion |

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

- 64 endpoints implemented across all modules
- Full read and write layer (GET, POST, PUT, PATCH and DELETE)
- Authentication endpoint with email/password validation
- Soft delete strategy with dedicated inactive endpoints
- Pagination and sorting support on listing endpoints
- Bean Validation on request DTOs
- Dynamic filtering with JPA Specification + Join
- Promotion system with `DIRECT_PRICE` and `QUANTITY_DISCOUNT`
- Global exception handling with custom exceptions
- Swagger / OpenAPI documentation

**Planned next steps:**

- Spring Security + JWT + BCrypt
- Migration to PostgreSQL
- Database versioning with Flyway
- Automated tests
- Performance and documentation improvements

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