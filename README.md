# projeto-b2


## Stack

- Java 11
- Maven (v3.8.6)
- Spring Boot (v2.7.5)
- JUnit (v5)
- Swagger

## Run
Para executar o projeto execute o seguinte comando na raiz do projeto
```sh
./mvnw spring-boot:run
```

Ou execute o programa na sua IDE de preferencia.

## Documentação 

Acesse o seguintre link para acessar a documentação da api
```sh
http://localhost:8080/swagger-ui/index.html
```

## Paths - Pay

| Função | Caminho |
| ------ | ------ |
| POST | /pay |

### Inserção de um produto e pagamento
POST /pay
```
{
    "product" : {
        "id" : integer value,
        "name" : "string value",
        "price" : double value
    },
    "payment" : {
        "entry" : double value,
        "installments" : integer value
    }
}
```

