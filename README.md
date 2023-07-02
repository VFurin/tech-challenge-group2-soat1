# Documentação - Tech Challenge - Grupo 2 - PosTech - Arquitetura de Software - FIAP 
Repositório para o desafio do Tech Challenge da Pós-gradução em Software Architecture pela FIAP.

### Build do Container Docker
Para buildar o container da aplicação, utilize o comando a seguir:

```sh
docker-compose up -d
```

Após finalizar o start dos containers, será necessário realizar uma carga de dados iniciais na base, executando o seguinte comando:

```sh
docker exec -i tech-challenge-db mysql -u root -p[password] < .docker/seeds/load-data.sql
```

**Importante!**<br/>
- Esse comando é necessário ser executado apenas uma vez no up do compose. 
- Após a primeira inicialização, os volumes relacionados aos dados do MySQL estarão persistidos. 
- Somente será necessária a execução novamente se houver a remoção dos volumes mapeados.

### Rebuid do Container Docker
Para rebuidar o container da aplicação, utilize o comando a seguir:

```sh
docker-compose up -d --build
```

Após executar o comando acima, a aplicação estará disponível em http://localhost:8080/api.

## Documentação Swagger da API
A documentação em padrão Swagger está disponível em http://localhost:8080/api/swagger-ui.html.


# Endpoints disponíveis por recurso
Abaixo, segue a lista de endpoints disponíveis por recurso e exemplos de requisição.

## Recurso Clientes
Endpoints gerados para gestão de clientes na plataforma

##### Cadastrar clientes
```sh
POST http://localhost:8080/api/clientes

Request body
{
    "nome" : "Teste 1",
    "email" : "teste1@teste.com.br",
    "cpf" : 12345678911
}
```

##### Remover Clientes
```sh
DELETE http://localhost:8080/api/clientes/1
```

##### Busca de Cliente por CPF
```sh
GET http://localhost:8080/api/clientes/12345678911
```

##### Atualizar dados do clientes
```sh
PATCH http://localhost:8080/api/clientes/1

Request body
{
    "nome": "Teste 3",
    "email": "teste3@teste.com.br"
}
```

## Recurso Categorias
Endpoints gerados para a gestão de categorias na plataforma

##### Listar categorias
```sh
GET http://localhost:8080/api/categorias
```

## Recurso Produtos
Endpoints gerados para a gestão de produtos na plataforma

##### Listagem de Produtos
```sh
GET http://localhost:8080/api/produtos
```

##### Listagem de Produtos por código de categoria
```sh
GET http://localhost:8080/api/produtos/categorias/codigo/1
```

##### Listagem de Produtos por nome de categoria
```sh
GET http://localhost:8080/api/produtos/categorias/nome/Bebida
```

##### Adicionar produtos
```sh
POST http://localhost:8080/api/produtos

Request body
{
 "nome": "Cheeseburguer 150G",
 "categoriaId": 1,
 "preco" : "39.90",
 "descricao" : "Hamburguer de 150G com duas fatias de queijo cheedar",
 "imagem" : "/imgs/products/cheeseburguer.png"
 
}
```

##### Atualizar produtos
```sh
PUT http://localhost:8080/api/produtos/1

Request body
{
 "nome": "Cheeseburguer 120G",
 "categoriaId": 1,
 "preco" : "35.90",
 "descricao" : "Hamburguer de 120G com duas fatias de queijo cheedar",
 "imagem" : "/imgs/products/cheeseburguer.png"
 
}
```

##### Remover produtos
```sh
DELETE http://localhost:8080/api/produtos/1
```

## Recurso Pedidos
Endpoints gerados para gestão de pedidos na plataforma

##### Listagem de Pedidos
Para listar todos os pedidos:

```sh
GET http://localhost:8080/api/pedidos
```

Para listar os pedidos, filtrando pelo status do pedido:

```sh
GET http://localhost:8080/api/pedidos?status={status}
```

Os possíveis status dos pedidos são: REALIZADO, CANCELADO, PREPARACAO e PRONTO.

Para buscar um pedido pelo id:

```sh
GET http://localhost:8080/api/pedidos/{id}
```

##### Atualizar status do pedido

```sh
PATCH http://localhost:8080/api/pedidos/1/status

Request body: 
{
    "status": "PRONTO"
}
```

Os possíveis status dos pedidos são: REALIZADO, CANCELADO, PREPARACAO e PRONTO.

##### Adicionar items ao pedido

```sh
POST http://localhost:8080/api/pedidos/1/items

Request body: 
{
    "quantidade" : 1,
    "produtoId" : 2 
}
```

##### Atualizar items do pedido

```sh
PUT http://localhost:8080/api/pedidos/1/items

Request body: 
{
    "quantidade" : 2,
    "produtoId" : 2 
}
```

##### Remover items do pedido

```sh
DELETE http://localhost:8080/api/pedidos/1/items

Request body: 
{
    "produtoId" : 2 
}
```

**Importante!** <br/>
- Somente é possível adicionar itens ao pedido depois se o pedido estiver em status **RECEBIDO**. O que indica que já houve uma solicitação de pedido que ainda não foi efetivado o pagamento. Permitindo que seja realizada a manutenção de itens.
- Caso o pedido esteja em outro status, será entendido que o pedido esteja em outro estado na esteira e não permitirá mais a manutenção de itens.

## Recurso Checkout
Endpoints gerados para o processo de checkout de pedidos na plataforma

##### Checkout
```sh
POST http://localhost:8080/api/checkout

Request body:
{
    "itens" :[
        {
            "quantidade": 2,
            "produtoId": 1
        },
        {
            "quantidade": 1,
            "produtoId": 2
        }
    ],
    "clienteId": 1
}
```

**Importante!** <br/>
- O processo de checkout irá efetivamente gerar o pedido na plataforma com a lista de itens e quantidade informadas no payload. <br/>
- O pedido gerado será iniciado com status **RECEBIDO**.<br/>
- Enquanto o status estiver como **RECEBIDO** será possível realizar a manutenção dos itens do pedido através dos endpoints disponibilizados no recurso pedidos.<br/>
- Somente será considerado um pedido como geração quando ocorrer o acionamento do endpoint relacionado ao recurso de pagamentos.

## Recurso Pagamentos
Endpoints gerados para gestão de pagamentos na plataforma

##### Listar tipos de pagamento
```sh
GET http://localhost:8080/api/pagamentos/tipos-pagamento
```

##### Efetuar pagamento de um pedido
```sh
PUT http://localhost:8080/api/pagamentos/pedidos/1

Request body:
{
    "pagamentoId": 1
}
```

**Importante!**<br/>
- Após realizar requisição do pagamento, o status do pedido será alterado para **GERAÇÃO**. Esse status **não permite** mais manutenção no pedido.
- O pedido, após efetivar o pagamento, será encaminhado a cozinha para que seja preparado.
- Para alteração do status do pedido, utilizar os endpoints disponibilizados no recurso pedidos.