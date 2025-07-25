# stock-microservice
microserviço de gerenciamento de stock

## FIAP - Tech Challenge - Fase 4

### Sistema de gestão para seus estabelecimentos

Nessa quarta fase de entrega o objetivo é desenvolver um backend de Sistema de Gerenciamento de Pedidos Integrado
com Spring e Microsserviços dividido em 6 microserviços.

### Serviço de stock
Serviço responsável por ser o gerenciamento de stock

### Como rodar o projeto
Para rodar o projeto completo é necessário baixar os 6 microsserviços e rodar a partir do arquivo docker-compose que se encontra no repositório de [customer](https://github.com/MaiconFiuza/customer-microservice)

#### 1. Fazer o build dos containeres da aplicação:
Executar o seguinte comando:
    
    docker-compose up --build

#### 2. Executar a aplicação através dos containeres criados:
Executar o seguinte comando para inicializar os containeres da aplicação

    docker-compose up


Serviço de customer
Disponível na porta http://localhost:8082/

Link para a documentação das API's do projeto (OpenAPI):
[http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)



### Cobertura de testes do projeto 
Para rodar a cobertura de testes do projeto é possível pelo comando mvn test, o report com a porcentagem de testes coberto estará no arquivo index dentro de `target\site\jacoco`
<img width="1143" height="492" alt="image" src="https://github.com/user-attachments/assets/00acf228-7ebc-4111-a8af-33e32fb2f1eb" />





