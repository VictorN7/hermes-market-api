
# 🛒 Hermes Market API

O Hermes Market é uma API REST desenvolvida com Java 21 e Spring Boot que simula o backend de um marketplace.

O projeto foi concebido para aplicar conceitos utilizados em aplicações corporativas, como arquitetura em camadas, modelagem de domínio, separação de responsabilidades, persistência com PostgreSQL, versionamento do banco de dados com Flyway e documentação da API com OpenAPI/Swagger.

Atualmente, a API contempla módulos para gerenciamento de usuários, endereços, categorias, marcas, produtos, promoções e pedidos, enquanto a camada de segurança baseada em Spring Security e JWT está sendo implementada de forma incremental.


![Java](https://img.shields.io/badge/Java-21-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![License](https://img.shields.io/badge/license-MIT-green)

----

## ⚙️ Tecnologias

### Linguagem
- Java 21

### Framework
- Spring Boot
- Spring Web
- Spring Data JPA

### Segurança
- Spring Security
- JWT

### Persistência
- Hibernate
- PostgreSQL
- Flyway

### Documentação
- OpenAPI / Swagger

### Ferramentas
- Maven
- Lombok
- Bean Validation

----

## 📌 Principais Recursos

- Arquitetura preparada para autenticação e autorização com Spring Security
- Implementação gradual de autenticação baseada em JWT
- Estrutura preparada para criptografia de senhas com BCrypt
- Arquitetura em camadas
- DTOs para requisições e respostas
- Mapeamento entre entidades e DTOs com Mappers
- Filtros dinâmicos utilizando Spring Data JPA Specifications
- Paginação e ordenação
- Soft Delete para entidades
- Tratamento global de exceções
- Documentação da API com OpenAPI / Swagger

---

## 📊 Projeto em Números

- ✅ Mais de 70 endpoints REST
- ✅ 18 migrations com Flyway
- ✅ 8 entidades de domínio
- ✅ Arquitetura organizada em 6 camadas
- ✅ Controllers separados para Cliente, Administrador, Autenticação e Operações Internas
- ✅ Documentação completa com Swagger/OpenAPI

---

## 🏛️ Arquitetura

O Hermes Market segue uma arquitetura em camadas, promovendo separação de responsabilidades, baixo acoplamento e facilidade de manutenção.

```text
com.hermes.market
│
├── application
│   ├── dto
│   ├── exception
│   ├── mapper
│   └── service
│
├── config
│
├── domain
│   ├── order
│   ├── product
│   └── user
│
├── infrastructure
│   └── repository
│       └── specification
│
└── web
    └── controller
        ├── admin
        ├── auth
        ├── client
        ├── system
        └── exception
```

### Organização das Camadas

| Camada             | Responsabilidade                                                                                                                                      |
| ------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| **application**    | Contém os serviços da aplicação, DTOs, mappers e exceções utilizadas pelos casos de uso.                                                              |
| **config**         | Configurações da aplicação, incluindo Spring Security e configurações de ambiente.                                                                    |
| **domain**         | Entidades, enums e regras de negócio organizadas por contexto (`user`, `product` e `order`).                                                          |
| **infrastructure** | Repositórios JPA e Specifications responsáveis pela persistência e consultas dinâmicas.                                                               |
| **web**            | Controllers REST organizados por responsabilidade (Cliente, Administrador, Autenticação e Operações Internas), além do tratamento global de exceções. |


---

## 🧩 Modelo de Domínio

O Hermes Market foi modelado para representar os principais processos de um marketplace.

| Entidade      | Descrição                                                           |
| ------------- | ------------------------------------------------------------------- |
| **User**      | Usuário do sistema com autenticação, perfil de acesso e status.     |
| **Address**   | Endereços cadastrados pelos usuários para entrega de pedidos.       |
| **Category**  | Categoria utilizada para organização dos produtos.                  |
| **Brand**     | Marca responsável pelos produtos cadastrados.                       |
| **Product**   | Produto comercializado no marketplace.                              |
| **Promotion** | Promoções aplicadas aos produtos com diferentes regras de desconto. |
| **Order**     | Pedido realizado pelo usuário.                                      |
| **OrderItem** | Item pertencente a um pedido.                                       |

### Principais Relacionamentos

* Um **User** pode possuir vários **Address**.
* Um **User** pode realizar vários **Order**.
* Um **Order** possui vários **OrderItem**.
* Cada **OrderItem** referencia um **Product**.
* Um **Product** pertence a uma **Category** e a uma **Brand**.
* Um **Product** pode participar de várias **Promotion**.

---

## 🔐 Segurança

A camada de segurança está sendo implementada de forma incremental utilizando Spring Security, JWT e BCrypt.

### Recursos implementados

- Estrutura inicial do Spring Security
- Processo de autenticação em implementação
- Base para controle de acesso da aplicação

### Próximas implementações

- Geração e validação completa de JWT
- Autorização baseada em perfis (`ADMIN` e `CLIENT`)
- Criptografia de senhas com BCrypt

---

## ✅ Boas Práticas

Durante o desenvolvimento do projeto foram adotadas práticas com foco em organização, escalabilidade e facilidade de manutenção.

- Arquitetura em camadas
- Separação de responsabilidades
- DTOs para entrada e saída de dados
- Mappers dedicados
- Services contendo regras de negócio
- Specifications para consultas dinâmicas
- Paginação e ordenação
- Soft Delete
- Bean Validation
- Tratamento global de exceções
- Documentação automática com Swagger/OpenAPI

---

## 🏷️ Sistema de Promoções

O Hermes Market suporta dois modelos de promoção, permitindo representar diferentes estratégias comerciais.

| Tipo | Descrição |
|------|-----------|
| `DIRECT_PRICE` | Define um preço promocional fixo para o produto. |
| `QUANTITY_DISCOUNT` | Aplica desconto quando uma quantidade mínima é atingida. |

### Regras de negócio

- O preço promocional deve ser inferior ao preço original.
- A data de término deve ser posterior à data de início.
- Um produto não pode possuir duas promoções ativas simultaneamente.

---

## 🛡️ Tratamento de Exceções

A API possui tratamento global de exceções utilizando `@ControllerAdvice`, garantindo respostas padronizadas para erros de validação, regras de negócio e exceções inesperadas.

| Status | Situação |
|---------|----------|
| 400 | Dados inválidos |
| 404 | Recurso não encontrado |
| 409 | Violação de regra de negócio |
| 500 | Erro interno da aplicação |

---

# 📋 Endpoints da API

## 🔐 Autenticação

| Método | Endpoint                | Descrição                                |
| ------ |-------------------------|------------------------------------------|
| POST   | `/api/v1/auth/login`    | Autenticar usuário usando e-mail e senha |
| POST   | `/api/v1/auth/register` | Criar novo usuário do sistema            |

---

## 👤 Endpoints do Cliente

### Usuários

| Método | Endpoint                                   | Descrição                                                     |
| ------ | ------------------------------------------ | ------------------------------------------------------------- |
| GET    | `/api/v1/users/{id}`                       | Buscar usuário ativo por id                                  |
| GET    | `/api/v1/users/{id}/orders`                | Listar pedidos do usuário                                    |
| GET    | `/api/v1/users/{id}/addresses`             | Listar endereços do usuário                                  |
| GET    | `/api/v1/users/{id}/addresses/{addressId}` | Buscar endereço por id                                       |
| POST   | `/api/v1/users`                            | Criar um novo usuário                                        |
| POST   | `/api/v1/users/{id}/addresses`             | Adicionar um novo endereço ao usuário                        |
| PUT    | `/api/v1/users/{id}`                       | Atualizar informações do usuário                             |
| PUT    | `/api/v1/users/{id}/addresses/{addressId}` | Atualizar informações do endereço                            |
| PATCH  | `/api/v1/users/{id}/password`              | Alterar senha do usuário                                     |
| PATCH  | `/api/v1/users/{id}/deactivate`            | Desativar conta do usuário                                   |
| DELETE | `/api/v1/users/{id}`                       | Deletar usuário se não houver compras, caso contrário desativar |
| DELETE | `/api/v1/users/{id}/addresses/{addressId}` | Deletar endereço se nunca foi usado em um pedido            |

### Categorias

| Método | Endpoint                           | Descrição                      |
| ------ | ---------------------------------- | ------------------------------ |
| GET    | `/api/v1/categories`               | Listar todas as categorias ativas |
| GET    | `/api/v1/categories/{id}`          | Buscar categoria ativa por id  |
| GET    | `/api/v1/categories/{id}/products` | Listar produtos por categoria   |

### Marcas

| Método | Endpoint                       | Descrição                 |
| ------ | ------------------------------ | ------------------------- |
| GET    | `/api/v1/brands`               | Listar todas as marcas ativas |
| GET    | `/api/v1/brands/{id}`          | Buscar marca ativa por id |
| GET    | `/api/v1/brands/{id}/products` | Listar produtos por marca |

### Produtos

| Método | Endpoint                | Descrição                         |
| ------ | ----------------------- | --------------------------------- |
| GET    | `/api/v1/products`      | Listar produtos com filtros opcionais |
| GET    | `/api/v1/products/{id}` | Buscar produto por id            |

### Pedidos

| Método | Endpoint                                      | Descrição                         |
| ------ | --------------------------------------------- | --------------------------------- |
| GET    | `/api/v1/orders/{id}`                         | Buscar pedido por id              |
| POST   | `/api/v1/orders`                              | Criar um novo pedido              |
| POST   | `/api/v1/orders/{id}/items`                   | Adicionar item ao pedido          |
| PATCH  | `/api/v1/orders/{id}/items/{itemId}/quantity` | Atualizar quantidade do item      |
| PATCH  | `/api/v1/orders/{id}/cancel`                  | Cancelar pedido quando status for CREATED |
| DELETE | `/api/v1/orders/{id}/items/{itemId}`          | Remover item quando status for CREATED |

### Promoções

| Método | Endpoint                           | Descrição                      |
| ------ | ---------------------------------- | ------------------------------ |
| GET    | `/api/v1/promotions`               | Listar todas as promoções ativas |
| GET    | `/api/v1/promotions/{id}`          | Buscar promoção ativa por id  |
| GET    | `/api/v1/promotions/{id}/products` | Listar produtos da promoção   |

### Pedidos Internos (Sistema)

| Método | Endpoint                               | Descrição                 |
| ------ | -------------------------------------- | --------------------------- |
| PATCH  | `/api/v1/internal/orders/{id}/pay`     | Marcar pedido como pago     |
| PATCH  | `/api/v1/internal/orders/{id}/ship`    | Marcar pedido como enviado  |
| PATCH  | `/api/v1/internal/orders/{id}/deliver` | Marcar pedido como entregue |

---

## 🔧 Endpoints Administrativos

### Usuários Admin

| Método | Endpoint                            | Descrição                 |
| ------ | ----------------------------------- | ------------------------- |
| GET    | `/api/v1/admin/users`               | Listar todos os usuários  |
| GET    | `/api/v1/admin/users/{id}`          | Buscar usuário por id     |
| GET    | `/api/v1/admin/users/inactive`      | Listar usuários inativos  |
| GET    | `/api/v1/admin/users/inactive/{id}` | Buscar usuário inativo por id |
| GET    | `/api/v1/admin/users/blocked`       | Listar usuários bloqueados |
| PATCH  | `/api/v1/admin/users/{id}/activate` | Ativar usuário            |
| PATCH  | `/api/v1/admin/users/{id}/block`    | Bloquear usuário          |
| PATCH  | `/api/v1/admin/users/{id}/unlock`   | Desbloquear usuário       |

### Categorias Admin

| Método | Endpoint                                   | Descrição                           |
| ------ | ------------------------------------------ | ----------------------------------- |
| GET    | `/api/v1/admin/categories/inactive`        | Listar categorias inativas         |
| GET    | `/api/v1/admin/categories/inactive/{id}`   | Buscar categoria inativa por id    |
| POST   | `/api/v1/admin/categories`                 | Criar uma nova categoria           |
| PATCH  | `/api/v1/admin/categories/{id}/name`       | Atualizar nome da categoria        |
| PATCH  | `/api/v1/admin/categories/{id}/activate`   | Ativar categoria                   |
| PATCH  | `/api/v1/admin/categories/{id}/deactivate` | Desativar categoria               |
| DELETE | `/api/v1/admin/categories/{id}`            | Deletar categoria se não tiver produtos |

### Marcas Admin

| Método | Endpoint                               | Descrição                       |
| ------ | -------------------------------------- | ------------------------------- |
| GET    | `/api/v1/admin/brands/inactive`        | Listar marcas inativas         |
| GET    | `/api/v1/admin/brands/inactive/{id}`   | Buscar marca inativa por id     |
| POST   | `/api/v1/admin/brands`                 | Criar uma nova marca           |
| PATCH  | `/api/v1/admin/brands/{id}/name`       | Atualizar nome da marca        |
| PATCH  | `/api/v1/admin/brands/{id}/activate`   | Ativar marca                   |
| PATCH  | `/api/v1/admin/brands/{id}/deactivate` | Desativar marca               |
| DELETE | `/api/v1/admin/brands/{id}`            | Deletar marca se não tiver produtos |

### Produtos Admin

| Método | Endpoint                                 | Descrição                      |
| ------ | ---------------------------------------- | ------------------------------ |
| GET    | `/api/v1/admin/products/inactive`        | Listar produtos inativos       |
| GET    | `/api/v1/admin/products/inactive/{id}`   | Buscar produto inativo por id  |
| POST   | `/api/v1/admin/products`                 | Criar um novo produto          |
| PUT    | `/api/v1/admin/products/{id}`            | Atualizar completamente o produto |
| PATCH  | `/api/v1/admin/products/{id}/stock`      | Atualizar quantidade em estoque |
| PATCH  | `/api/v1/admin/products/{id}/activate`   | Ativar produto                |
| PATCH  | `/api/v1/admin/products/{id}/deactivate` | Desativar produto            |
| DELETE | `/api/v1/admin/products/{id}`            | Deletar produto se nunca foi vendido |

### Pedidos Admin

| Método | Endpoint                                   | Descrição                          |
| ------ | ------------------------------------------ | ---------------------------------- |
| GET    | `/api/v1/admin/orders`                     | Listar todos os pedidos            |
| GET    | `/api/v1/admin/orders/{id}`                | Buscar pedido por id              |
| GET    | `/api/v1/admin/orders/inactive-users`      | Listar pedidos de usuários inativos |
| GET    | `/api/v1/admin/orders/inactive-users/{id}` | Buscar pedido de usuário inativo por id |

### Promoções Admin

| Método | Endpoint                                                      | Descrição                         |
| ------ | ------------------------------------------------------------- | --------------------------------- |
| GET    | `/api/v1/admin/promotions/inactive`                           | Listar promoções inativas        |
| GET    | `/api/v1/admin/promotions/inactive/{id}`                      | Buscar promoção inativa por id   |
| GET    | `/api/v1/admin/promotions/{id}/products`                      | Listar produtos da promoção      |
| POST   | `/api/v1/admin/promotions`                                    | Criar uma nova promoção          |
| POST   | `/api/v1/admin/promotions/{promotionId}/products`             | Adicionar produto à promoção     |
| PATCH  | `/api/v1/admin/promotions/{id}/activate`                      | Ativar promoção                  |
| PATCH  | `/api/v1/admin/promotions/{id}/deactivate`                    | Desativar promoção              |
| DELETE | `/api/v1/admin/promotions/{promotionId}/products/{productId}` | Remover produto da promoção     |

---

## 🎯 Objetivos

O Hermes Market foi desenvolvido com o objetivo de simular o backend de um marketplace real, aplicando conceitos utilizados em aplicações corporativas.

Entre os principais objetivos estão:

- Construção de APIs REST seguindo boas práticas.
- Modelagem de domínio voltada para regras de negócio.
- Organização do código utilizando arquitetura em camadas.
- Separação entre entidades e contratos da API através de DTOs.
- Evolução incremental da camada de segurança.
- Utilização de ferramentas amplamente adotadas no ecossistema Java, como Spring Boot, Spring Data JPA, Flyway e PostgreSQL.

---

## ▶️ Como executar

### 1️⃣ Clone o projeto

git clone ...

### 2️⃣ Configure o PostgreSQL

**Crie um banco chamado:**

`hermes_market`

---

### 3️⃣ Configure o application.properties

`spring.datasource.url=...`

---

### 4️⃣ Execute a aplicação

`mvn spring-boot:run`

**As migrations serão executadas automaticamente pelo Flyway.**

---

### 5️⃣ Acesse o Swagger

**Swagger**

`http://localhost:8080/swagger-ui/index.html`

---

## 👤 Autor

**Victor Hugo Nogueira Santos**

Backend Developer | Java & Spring Boot

[![LinkedIn](https://img.shields.io/badge/LinkedIn-victor--nogueira--ti-blue)](https://www.linkedin.com/in/victor-nogueira-ti/)
[![GitHub](https://img.shields.io/badge/GitHub-VictorN7-black)](https://github.com/VictorN7)