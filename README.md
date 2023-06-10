# Documentação - Tech Challenge - Grupo 2 - PosTech - Arquitetura de Software - FIAP 
Repositório para o desafio do Tech Challenge da Pós-gradução em Software Architecture pela FIAP.

### Build da Imagem Docker
Para buildar a imagem Docker da aplicação, execute o seguinte comando:

```sh
docker build -t tech-challenge .
```

### Rodando a Imagem Docker
Para executar a aplicação, utilize o comando a seguir:

```sh
docker run -p 8080:8080 --rm -it tech-challenge:latest
```

Após executar o comando acima, a aplicação estará disponível em http://localhost:8080.


## Cadastro de Clientes
Para cadastrar um cliente, utilize o seguinte endpoint:

```sh
POST http://localhost:8080/clientes
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
GET http://localhost:8080/clientes
```

## Busca de Cliente por CPF

Para buscar um cliente por CPF, utilize o seguinte endpoint:

```sh
GET http://localhost:8080/clientes?cpf=12345678911
```