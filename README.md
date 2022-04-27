# Finance - API de Controle financeiro

> API de Gestão  Financeira

Esse projeto tem por objetivo ser uma API simples de gestão financeira. Expoem alguns endpoints de CRUD de categorias de
despesas e receitas bem como endpoints de CRUD para os lancamentos financeiros. Por fim expoe um endpoint da
consolidação dos registros da data informada.

![](header.png)

## Setup do ambiente de Desenvolvimento

Primento faça o clone do projeto para o seu ambiente

```sh
 git clone https://github.com/gcbrandao/finance.git
```

Então importe ele para a sua IDE favorita.

Para fazer o build(contrução) da aplicação use os comandos abaixo. Esse comando ira gerar um arquivo .jar no diretorio
do seu projeto '/target':

```sh
  mvn -N wrapper:wrapper
 
 ./mvnw clean install
```

Para gerar a imagem do container use o comando abaixo:

```sh
 ./mvnw  spring-boot:build-image
```

Com o comando acima uma imagem contendo o conteudo da aplicação foi gerada no se docker local, sendo assim você ja tem o
necessário para iniciar a aplicação.

## Inicalizando a Aplicação

Para inicializar a aplicação use o comando abaixo. Essa aplicação esta contida em um container docker e ela usa o banco
de dados MySQL. No arquivo 'docker-compose.yml' estão todas as configurações necessárias para a aplicação providenciar o
banco de dados e o ambiente web que será o provedor da API Rest.

A aplicação usa o **Flyway** que ira automaticamente gerar o banco de dados e fazer um load de dados inicial para o
teste inical da API

```sh
 docker-compose up
```

## Instalação sem Docker

OS X & Linux & Windows:
Você pode executar o aplicativo a partir da linha de comando com o Maven. Você também pode construir um único arquivo
JAR executável que contém todas as dependências, classes e recursos necessários e os executar. Construir um jar(fat jar)
executável facilita o envio, versão e implantação do serviço como um aplicativo em todo o ciclo de vida de
desenvolvimento, em diferentes ambientes e assim por diante.

Obs: Esses passos são para caso você não tenha como usar o Docker. Nesse caso você pode rodar a aplicação diretamente do
seu terminal mas terá que providenciar uma instância de banco de dados MySQL8 e também terá que editar o arquivo '
application.properties' e arrumar a url de conexão.

Você pode fazer o build do JAR com:

```sh
./mvnw clean package
```

Com Maven, você pode rodar aplicação com o comando:

```sh
./mvnw spring-boot:run
```

Ou diretamente com o comando java abaixo:

```sh
 java -jar target/finance-0.0.1-SNAPSHOT.jar
```

## Exemplo de uso

**Testando a aplicação**

Agora que a aplicação esta rodando vamos testa-la. Sugiro usar o aplicativo Postman pra isso e inclusive ja acicionamos
no diretorio raiz da aplicação uma Collection do Postman pronta para uso. Encontre o arquivo abaixo e importe para o seu
Postman:

```sh
Finance.postman_collection.json
````

## Curl

**Caso não tenha o postman segue os exemplos de quisições usando Curl**

**Categorias:**

Listando todas as Categorias

```sh
curl --location --request GET 'http://localhost:8080/finance/categoria/lista'
```

Buscando uma Categoria especifica

```sh
curl --location --request GET 'http://localhost:8080/finance/categoria/1'
```

Adicionando uma Categoria nova

```sh
curl --location --request POST 'http://localhost:8080/finance/categoria' \
--header 'Content-Type: application/json' \
--data-raw '{
"nome" : "Teste novo"
}'
```

**Lançamentos:**

Listando todos os Lancamentos

```sh
curl --location --request GET 'http://localhost:8080/finance/lancamento/lista'
```

Buscando um Lancamento

```sh
curl --location --request GET 'http://localhost:8080/finance/lancamento/3'
```

Adicionando um Lancamento

```sh
curl --location --request POST 'http://localhost:8080/finance/lancamento' \
--header 'Content-Type: application/json' \
--data-raw '{
    "descricao": "Gasto com curso novo",
    "dataVencimento": "2017-03-10",
    "dataPagamento": "2017-03-10",
    "valor": 6600,
    "observacao": "Gasto com curso de java avancado",
    "tipo": "RECEITA",
    "categoria": {
        "codigo": 4
    }
}'
```

**Consolidação:**

Consulta Consolidada

```sh
curl --location --request GET 'http://localhost:8080/finance/lancamento/consolidado?dataConsolidacao=2022-04-26'
```

**Monitoramento:**

Para coletar dados de telemetria da aplicação ativamos o Actuator nessa aplicação e esse dados podem ser acessados pela
url abaixo

Consulta dados de telemetria da aplicação

```sh
curl --location --request GET 'http://localhost:8080/actuator/'
```

## Release History

* 0.0.1-SNAPSHOT
    * Work in progress

## Meta

Gilberto C Brandao – [@gcbandao](https://twitter.com/gcbandao) – gcbrandao@gmail.com

Distributed under the XYZ license. See ``LICENSE`` for more information.

[https://github.com/gcbrandao/](https://github.com/gcbrandao/)

## Contributing

1. Fork it ( git clone <https://github.com/gcbrandao/finance.git>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -m 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request

<!-- Markdown link & img dfn's -->

[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square

[npm-url]: https://npmjs.org/package/datadog-metrics

[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square

[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square

[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics

[wiki]: https://github.com/yourname/yourproject/wiki
