# 🛒 Hermes Market API

**Hermes Market API** é uma API REST backend em desenvolvimento para gerenciamento de um mercado/supermercado, criada com foco em boas práticas de arquitetura, modelagem de domínio (DDD) e Java com Spring Boot.

O projeto está sendo desenvolvido de forma incremental e pública, documentando decisões de arquitetura, evolução do domínio e aprendizados ao longo do processo.


## 🎯 Objetivo do Projeto

Construir um sistema completo de mercado que permita:

- Gerenciamento de usuários com perfis distintos (admin e cliente)
- Organização de produtos por categorias
- Criação e gerenciamento de pedidos
- Definição de métodos de pagamento e entrega
- Evolução consistente do domínio antes de regras complexas

O foco principal **não é apenas o CRUD**, mas sim:

**uma base sólida de domínio, relacionamentos bem definidos e código sustentável no longo prazo.**



## 🧩 Arquitetura do Projeto

O projeto segue uma organização inspirada em **DDD (Domain-Driven Design):**

- **domain**: entidades e regras de negócio
- **application_service**: serviços de aplicação
- **infrastructure.repository**: persistência e acesso a dados
- **web.controller**: camada de entrada (API REST)
- **config**: configuração e ambiente de testes

Essa organização permite evolução gradual do sistema sem acoplamento excessivo.

```
com.hermes.market
 ├─ domain
 │   ├─ user
 │   ├─ product
 │   └─ order
 │
 ├─ application
 │   └─ service
 │
 ├─ config
 │
 ├─ infrastructure
 │   └─ repository
 │
 └─ web
     └─ controller
```

## 📦 Estrutura Atual do Projeto

### 🔹 Entidades

- User
- Category
- Product
- Order
- OrderItem

### 🔹 Enums

- UserStatus
- Role
- ProductStatus
- CategoryStatus
- OrderStatus
- PaymentMethod
- DeliveryMethod

### 🔹 Controllers

- UserController
- CategoryController
- ProductController
- OrderController

### 🔹 Repositories

- UserRepository
- CategoryRepository
- ProductRepository
- OrderRepository
- OrderItemRepository

### 🔹 Configuração

- Ambiente de testes com H2
- Classe de configuração para carga inicial de dados
- Testes manuais e de integração dos endpoints


## 🔍 Funcionalidades Implementadas

- Cadastro e consulta de usuários
- Consulta de categorias
- Consulta de produtos
- Associação de produtos a categorias
- Criação de pedidos
- Inclusão de itens no pedido
- Cálculo automático do valor total do pedido
- Relacionamentos mapeados com JPA
- Diagrama UML utilizado como guia de desenvolvimento


## Endpoints Disponíveis

Os endpoints abaixo representam a primeira fase da API, focada em leitura e navegação dos dados:


### 🧑 Usuários

- GET /users
- GET /users/{id}

### 🗂️ Categorias

- GET /categories
- GET /categories/{id}
- GET /categories/{id}/products

### 📦 Produtos

- GET /products
- GET /products/{id}
- GET /products/category/{categoryId}

### 🧾 Pedidos
- GET /orders
- GET /orders/{id}


⚠️ **Alguns endpoints ainda estão em evolução e podem sofrer ajustes conforme o domínio amadurece.** 


## 🚧 Status do Projeto - Em desenvolvimento ativo

**O projeto está em fase inicial, com foco em:**

- Modelagem correta do domínio
- Relacionamentos bem definidos
- Separação clara de responsabilidades
- Regras mais complexas (validações, DTOs, segurança, etc.) estão planejadas para próximas etapas.


## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web (REST)
- Spring Data JPA
- Hibernate
- Banco de dados:
	- H2 (ambiente de testes)
	- PostgreSQL (planejado)
- Lombok
- Bean Validation
- Jackson (serialização JSON)


**Outras tecnologias serão adicionadas conforme a evolução do projeto.**

## ▶️ Próximos Passos

- Introduzir DTOs para controle de exposição de dados
- Melhorar validações de entrada
- Implementar regras de negócio mais complexas
- CRUD completo de produtos e categorias
- Implementar Flyway para versionamento de banco
- Documentação da API com Swagger / OpenAPI
- Implementar autenticação e autorização
- Evoluir testes automatizados


## 📌 Observações

Este projeto tem como principal objetivo aprendizado prático, mas sempre seguindo padrões utilizados em ambientes profissionais.

Commits, refatorações e decisões de design são feitos de forma consciente e documentada.






