# Documentação - Tech Challenge - Grupo 2 - PosTech - Arquitetura de Software - FIAP 
Repositório para o desafio do Tech Challenge da Pós-gradução em Software Architecture pela FIAP.

### Build do Container Docker
Para buildar o container da aplicação, utilize o comando a seguir:

```sh
docker-compose up -d
```

### Rebuid do Container Docker
Para rebuidar o container da aplicação, utilize o comando a seguir:

```sh
docker-compose up -d --build
```

Após executar o comando acima, a aplicação estará disponível em http://localhost:8080.


## Cadastro de Clientes
Para cadastrar um cliente, utilize o seguinte endpoint:

```sh
POST http://localhost:8080/api/clientes
```

Exemplo de payload:

```json
{
    "nome" : "Teste 1",
    "email" : "teste1@teste.com.br",
    "cpf" : 12345678911
}
```

## Listagem de Clientes

Para listar os clientes cadastrados, utilize o seguinte endpoint:

```sh
GET http://localhost:8080/api/clientes
```

## Busca de Cliente por CPF

Para buscar um cliente por CPF, utilize o seguinte endpoint:

```sh
GET http://localhost:8080/api/clientes/12345678911
```

## Listagem de Pedidos

Para listar todos os pedidos, utilize o seguinte endpoint:

```sh
GET http://localhost:8080/api/pedidos
```

Para listar os pedidos, filtrando pelo status do pedido, utilize o seguinte endpoint:

```sh
GET http://localhost:8080/api/pedidos?status={status}
```

Os possíveis status dos pedidos são: REALIZADO, CANCELADO, PREPARACAO e PRONTO.

Para buscar um pedido pelo id do pedido, utilize o seguinte endpoint:
```sh
GET http://localhost:8080/api/pedidos/{id}
```

## Atualizar status do pedido

Para atualizar o status de um pedido, utilize o seguinte endpoint:

```sh
PATCH http://localhost:8080/api/pedidos/status

Request body: 
{
    "status": "PRONTO"
}
```

Os possíveis status dos pedidos são: REALIZADO, CANCELADO, PREPARACAO e PRONTO.
