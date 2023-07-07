# Documentação - Tech Challenge - Grupo 2 SOAT1 - PosTech - Arquitetura de Software - FIAP 
Repositório para o desafio do Tech Challenge da Pós-gradução em Software Architecture pela FIAP.

## Introdução
Uma lanchonete de barrio que está expandido sua operação devido seu grande sucesso. Porém, com a expansão e sem um sistema de controle de pedidos, o atendimento aos clientes pode ser caótico e confuso.
Para solucionar o problema, a lanchonete irá investir em um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente.

## Membros do Grupo 2
- [Danilo Monteiro](https://github.com/dmonteirosouza)
- [Marcel Cozono](https://github.com/macozono)
- [Viviane Scarlatti](https://github.com/viviane-scarlatti)
- [Vinicius Furin](https://github.com/VFurin)
- [Vitor Walcker](https://github.com/VitorWalcker)

# Compilação e geração de artefato
Antes de iniciar, certifique-se de que sua máquina atenda aos seguintes requisitos:<br/><br/>

**JDK 11 instalado:**<br/>
Certifique-se de ter o JDK 11 instalado em sua máquina.<br/><br/>

**Maven >= 3 instalado:**<br/>
Verifique se você tem o Maven instalado em sua máquina. Para verificar a versão do Maven instalada, execute o seguinte comando no terminal: **mvn -version**. Se o Maven não estiver instalado ou estiver em uma versão inferior à 3, faça o download e siga as instruções de instalação do site oficial do Maven ou de outra fonte confiável.<br/><br/>

**Clonando o projeto:**<br/>
Clone o repositório do projeto em seu ambiente local.<br/><br/>

**Compilando o projeto:**<br/>
Navegue até o diretório raiz do projeto no terminal e execute o seguinte comando para compilar o projeto usando o Maven:<br/><br/>

```sh
mvn clean install
```

Isso irá baixar as dependências do projeto, compilar o código-fonte e criar o artefato no diretório target com o nome **tech-challenge-group2-soat1.jar**.<br/>
Esse artefato será copiado para a imagem do container em momento de build durante a execução do docker-compose.

**Executando o projeto:**<br/>
Após a conclusão da etapa anterior, você pode executar o projeto seguindo as instruções específicas do projeto.

# Build do Container Docker
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

## Execução do projeto via Postman
Basta clicar no link [diretório postman](src/main/resources/postman) onde está disponível o arquivo JSON contendo todos os endpoints configurados basta importa-lo via Postman e executar o passo-a-passo abaixo.

<a name="ancora"></a>
  1. [Cadastrar cliente](#ancora1)
  2. [Listar categorias](#ancora2)
  3. [Cadastrar produtos por categoria](#ancora3)
  4. [Listar produtos](#ancora4)
  5. [Checkout pedido informando os produtos](#ancora5)
  6. [Listar tipos de pagamento](#ancora6)
  7. [Efetuar pagamento](#ancora7)

## Informações adicionais
Algumas informações adicionais sobre a construção da API

### Conexão com banco de dados
Na API foi adicionado um parâmetro condicional para verificação de uma variável de ambiente chamada **DB_HOST**. Essa variável é injetada no container em momento de construção e é indicada com a URL para acesso ao banco de dados. Caso não seja encontrado um valor para essa variável, o valor **localhost:3306** será utilizado como **default**.<br/><br/>
Essa parametrização foi utilizada no caso se for necessário realizar testes via API localmente, sem a necessidade de estar inicializada no container.

### Profiles do Springboot
Foram criados dois profiles springboot para execução da API, sendo eles:

- **default**: A execução desse profile irá verificar pela conectividade com o banco de dados MySQL identificado pela variável de ambiente ou pelo localhost na porta 3306;
- **mock**: A execução desse profile irá iniciar o banco de dados H2 para testes. Esse profile pode ser utilizado em caso de testes unitários para persistência dos dados e memória.

# Endpoints disponíveis por recurso
Abaixo, segue a lista de endpoints disponíveis por recurso e exemplos de requisição.

## Recurso Clientes
Endpoints gerados para gestão de clientes na plataforma

<a id="ancora1"></a>
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
<a id="ancora2"></a>
##### Listar categorias
```sh
GET http://localhost:8080/api/categorias
```

## Recurso Produtos
Endpoints gerados para a gestão de produtos na plataforma
<a id="ancora4"></a>
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
<a id="ancora3"></a>
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

Request body
{
    "status": "PRONTO"
}
```

Os possíveis status dos pedidos são: REALIZADO, CANCELADO, PREPARACAO e PRONTO.

##### Adicionar items ao pedido

```sh
POST http://localhost:8080/api/pedidos/1/items

Request body
{
    "quantidade" : 1,
    "produtoId" : 2 
}
```

##### Atualizar items do pedido

```sh
PUT http://localhost:8080/api/pedidos/1/items

Request body
{
    "quantidade" : 2,
    "produtoId" : 2 
}
```

##### Remover items do pedido

```sh
DELETE http://localhost:8080/api/pedidos/1/items

Request body
{
    "produtoId" : 2 
}
```

**Importante!** <br/>
- Somente é possível adicionar itens ao pedido depois que o pedido estiver em status **RECEBIDO**. O que indica que já houve uma solicitação de pedido que ainda não foi efetivado o pagamento. Permitindo que seja realizada a manutenção de itens.
- Caso o pedido esteja em outro status, será entendido que o pedido esteja em outro estado na esteira e não permitirá mais a manutenção de itens.

## Recurso Checkout
Endpoints gerados para o processo de checkout de pedidos na plataforma

<a id="ancora5"></a>
##### Checkout
```sh
POST http://localhost:8080/api/checkout

Request body
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

<a id="ancora6"></a>
##### Listar tipos de pagamento
```sh
GET http://localhost:8080/api/pagamentos/tipos-pagamento
```
<a id="ancora7"></a>
##### Efetuar pagamento de um pedido
```sh
PUT http://localhost:8080/api/pagamentos/pedidos/1

Request body
{
    "pagamentoId": 1
}
```

**Importante!**<br/>
- Após realizar requisição do pagamento, o status do pedido será alterado para **GERAÇÃO**. Esse status **não permite** mais manutenção no pedido.
- O pedido, após efetivar o pagamento, será encaminhado a cozinha para que seja preparado.
- Para alteração do status do pedido, utilizar os endpoints disponibilizados no recurso pedidos.