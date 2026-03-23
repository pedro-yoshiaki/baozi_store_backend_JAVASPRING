# 🥟 Baozi Store — API REST

API REST desenvolvida para o trabalho da disciplina de **Desenvolvimento Web Back-End** (UNINTER), utilizando Java com Spring Boot.

## 🛠️ Tecnologias

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Lombok
- Maven

## 📦 Estrutura do Projeto

```
src/main/java/com/uninter/baozi/
├── model/          → Entidades JPA (Cliente, Produto, Pedido)
├── repository/     → Interfaces Spring Data JPA
├── service/        → Lógica de negócio
└── controller/     → Endpoints REST
```

## ⚙️ Como Executar

1. Certifique-se de ter o **MySQL** rodando localmente
2. Ajuste usuário e senha em `src/main/resources/application.properties`
3. Execute o projeto:
```bash
./mvnw spring-boot:run
```
O banco `baozi_store` será criado automaticamente.

## 🌐 Endpoints

### Clientes — `/clientes`
| Método | Rota           | Descrição         |
|--------|----------------|-------------------|
| POST   | /clientes      | Criar cliente     |
| GET    | /clientes      | Listar todos      |
| GET    | /clientes/{id} | Buscar por ID     |
| PUT    | /clientes/{id} | Atualizar         |
| DELETE | /clientes/{id} | Deletar           |

### Produtos — `/produtos`
| Método | Rota           | Descrição         |
|--------|----------------|-------------------|
| POST   | /produtos      | Criar produto     |
| GET    | /produtos      | Listar todos      |
| GET    | /produtos/{id} | Buscar por ID     |
| PUT    | /produtos/{id} | Atualizar         |
| DELETE | /produtos/{id} | Deletar           |

### Pedidos — `/pedidos`
| Método | Rota           | Descrição         |
|--------|----------------|-------------------|
| POST   | /pedidos       | Criar pedido      |
| GET    | /pedidos       | Listar todos      |
| GET    | /pedidos/{id}  | Buscar por ID     |
| DELETE | /pedidos/{id}  | Deletar           |

## 📋 Exemplos de Requisição (Postman)

**Criar Cliente:**
```json
POST /clientes
{
  "nome": "SeuNome123456",
  "clienteDesde": "2024-03-21"
}
```

**Criar Produto:**
```json
POST /produtos
{
  "nome": "Baozi Tradicional",
  "preco": 5.90,
  "estoque": true
}
```

**Criar Pedido:**
```json
POST /pedidos
{
  "clienteId": 1,
  "produtoId": 1,
  "quantidade": 3
}
```
