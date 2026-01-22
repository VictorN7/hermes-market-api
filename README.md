# Hermes Market API ⚡

**Hermes** é um sistema backend de gerenciamento de mercado em desenvolvimento, criado para praticar **Java 17 + Spring Boot**, modelagem de domínio e boas práticas de API REST.

O sistema permite que usuários façam pedidos de compras e agendem um horário para retirar os produtos ou receber a entrega, desde que o agendamento seja realizado **pelo menos 1 hora após o pedido**, garantindo que o mercado tenha tempo para organizar os itens.

## 🎯 Objetivo
Construir um sistema funcional, evoluindo o projeto de forma incremental e pública, enquanto pratico tecnologias e padrões utilizados no mercado.

## 🧱 Estrutura atual
- Entidades: User
- Enums: StatusUser, Role
- Projeto em fase inicial, focado na modelagem e regras básicas de negócio

## 🚧 Status do projeto
- Inicial, funcionalidades básicas em desenvolvimento
- CRUD inicial de produtos e usuários
- Planejamento de endpoints para pedidos e agendamento

## 🛠️ Tecnologias e conceitos
- Java 17+
- Spring Boot
- Spring Web (REST)
- Spring Data JPA
- Banco de dados: PostgreSQL ou MySQL
- Flyway (migrações) – será aplicado em versões futuras
- Lombok
- DTOs e mapeamento
- Bean Validation
- Swagger / OpenAPI

> Algumas tecnologias já estão configuradas no projeto, outras serão aplicadas conforme a evolução das funcionalidades.

## ▶️ Próximos passos
- CRUD completo de produtos
- Endpoints de usuários e pedidos
- Validação de horários para agendamento
- Implementação do Flyway para migração de banco
- Documentação completa via Swagger
- Evolução incremental e commits públicos no GitHub
