# FIAP Pagamentos

Este é um microsserviço responsável por realizar cobranças.

## Subindo o banco de dados

Basta rodar o comando abaixo na raiz do projeto para subir a instância do DynamoDB + GUI para ver os dados:

```shell
docker compose up database database-gui --build -d
```

## Como rodar os testes da aplicação

### Via linha de comando
Dentro do diretório do projeto, executar o comando abaixo:

```shell
.\gradlew test
```

Após a execução do comando, é possível visualizar o relatório de execução dos testes, no formato HTML, localizado em:

```shell
> .\build\reports\tests\test\index.html
```

## Cobertura de testes do projeto

Os testes foram construídos, nas diferentes camadas do projeto, utilizando:
- Testes unitários
- Testes de integração
- Testes de comportamento (BDD)

O resumo dos testes de integração e unitários pode ser observado em:
![image](https://github.com/joasgarcia/fiap-pagamento/assets/6011412/d1bc3a61-8fe8-4aa3-896f-828e9b9d2b64)

_Os testes de comportamento não constam no resumo acima._