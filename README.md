# 🛒 Hermes Market API

Hermes Market API é uma API REST desenvolvida com Java e Spring Boot que simula um sistema de marketplace.

O projeto é construído com foco em organização arquitetural, separação de responsabilidades e evolução incremental do domínio.

A aplicação já passou da fase inicial de modelagem e atualmente possui:

- Camada de aplicação bem definida
- Uso consistente de DTOs
- Separação clara entre respostas resumidas e detalhadas
- Filtros dinâmicos com Specification
- Estrutura preparada para crescimento (segurança, banco real, testes automatizados)

## 🎯 Objetivo do Projeto

**Construir uma API robusta que permita:**

- Gerenciamento de usuários
- Organização de produtos por categorias e marcas
- Consulta estruturada de pedidos
- Filtros dinâmicos e combináveis
- Evolução sustentável da base de código

**O foco principal não é apenas CRUD, mas:**

- Modelagem consistente de domínio
- Separação de responsabilidades
- Baixo acoplamento
- Arquitetura preparada para escalar

## 🧩 Arquitetura do Projeto

O projeto segue uma organização inspirada em DDD (Domain-Driven Design) com separação em camadas bem definidas.

## 📦 Estrutura de Pacotes

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
## ⚙️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database (em memória)
- Maven
- Lombok
- Bean Validation
- Jackson

## 🔎 Responsabilidade de Cada Camada

### 🔹 Domain

- Entidades JPA
- Enums
- Relacionamentos
- Regras essenciais do núcleo do sistema
- Essa camada não depende de Controller ou DTO.

### 🔹 Application

- Orquestração da lógica
- Serviços de aplicação
- DTOs de Request e Response
- Mapeamentos (Mapper)
- Exceções customizadas

Aqui está concentrada a regra de negócio da aplicação.

### 🔹 Infrastructure

- Repositories JPA
- Implementações com Specification
- Integração com banco de dados

A camada de domínio não depende diretamente da implementação da persistência.

### 🔹 Web

- Controllers REST
- Exposição HTTP
- Conversão de entrada/saída via DTO

Os controllers não possuem regra de negócio.

## 📦 Estrutura Atual do Domínio

### 🔹 Entidades

- User
- Category
- Product
- Brand
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

### 🔹 Relacionamentos Principais

- Product → pertence a uma Category
- Product → pertence a uma Brand
- Order → pertence a um User
- Order → possui múltiplos OrderItems
- OrderItem → referencia Product

Os relacionamentos estão mapeados com JPA utilizando boas práticas de associação.

## 🔍 Funcionalidades Implementadas

### ✔️ Camada de Leitura Completa (GET)

Todos os endpoints GET estão implementados.

### ✔️ DTOs de Resposta

A API não expõe entidades diretamente.
São utilizados DTOs específicos para cada contexto:

- Responses resumidas (Summary)
- Responses detalhadas (Detail)
- Responses de menu (MenuResponse)

Isso evita overfetching e exposição desnecessária de dados.

### ✔️ Filtros Dinâmicos com Specification

A listagem de produtos permite filtros combináveis via query params.

Exemplo:

- GET /api/v1/products?brandId=2&categoryId=1&name=lar

Os filtros são opcionais e podem ser combinados dinamicamente.

A implementação utiliza:

- Spring Data JPA
- Specification API
- Construção dinâmica de predicates

### ✔️ Separação de Respostas

Exemplo de estratégia adotada:

- ProductSummaryResponse → usado em listagens
- ProductResponse → usado em detalhamento
- BrandMenuResponse → usado para menus simples
- BrandDetailResponse → usado em página específica

Essa separação melhora performance e organização da API.

## 🔄 Fluxo Interno de uma Requisição

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
Database

Service
   ↓
Mapper
   ↓
DTO Response

```


## 🌐 Endpoints Disponíveis

### 🧑 Usuários

- GET /api/v1/users
- GET /api/v1/users/{id}

### 🗂️ Categorias

- GET /api/v1/categories
- GET /api/v1/categories/{id}

### 🏷️ Marcas

- GET /api/v1/brands
- GET /api/v1/brands/{id}

### 📦 Produtos

- GET /api/v1/products
- GET /api/v1/products/{id}

Com suporte a filtros opcionais:

- brandId
- categoryId
- name

### 🧾 Pedidos

- GET /api/v1/orders
- GET /api/v1/orders/{id}

As respostas de pedidos incluem:

- Informações do usuário
- Lista de itens
- Produto de cada item
- Valor total calculado

## 🛠️ Configuração e Ambiente

### 🔹 Banco de Dados

- H2 em memória (ambiente de desenvolvimento)
- Console H2 habilitado
- Estrutura preparada para migração futura para PostgreSQL

### 🔹 Inicialização de Dados

Existe classe de configuração responsável por carga inicial de dados para testes e navegação dos endpoints.

## 🧠 Decisões Arquiteturais Importantes

- Controllers não contêm regra de negócio
- DTOs evitam exposição de entidades
- Camada de aplicação centraliza orquestração
- Specification permite escalabilidade de filtros

Estrutura modular facilita introdução futura de:

- Segurança
- Paginação
- Versionamento de banco
- Testes automatizados

## 🚧 Status Atual do Projeto

Atualmente o projeto encontra-se na fase de:

- Consolidação da camada de leitura
- Refinamento de DTOs
- Organização arquitetural

Funcionalidades planejadas para próximas iterações:

- Endpoints de escrita (POST, PUT, DELETE e PATCH)
- Segurança com autenticação
- Paginação e ordenação
- Versionamento com Flyway
- Documentação Swagger
- Testes automatizados

## ▶️ Como Executar o Projeto

### 1️⃣ Clonar o repositório

```
git clone https://github.com/VictorN7/hermes-market-api.git
```

### 2️⃣ Acessar a pasta

```
cd hermes-market-api
```

### 3️⃣ Executar
```
./mvnw spring-boot:run
```

**A aplicação ficará disponível em:**

```
http://localhost:8080
```

**Console H2:**

```
http://localhost:8080/h2-console
```

## 📌 Observações Finais

Este projeto tem como principal objetivo aprendizado prático com aplicação de padrões profissionais.

A evolução é incremental e focada em qualidade estrutural antes de complexidade funcional.

Refatorações são parte ativa do processo de construção.
