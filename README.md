# API Desafio Sicredi


# Sobre o projeto

API Rest para gerenciamento de Votação de Pautas 


## Arquitetura:  
![Arc 1](https://github.com/naicson88/desafio-sicredi/blob/master/assets/arc.png) 

## Observações
    - Os dados de acessos e algumas variáveis foram colocadas hardcode apenas a título de agilizar o desenvolvimento.
    - O código (Classes, variáveis, métodos...) foram escritos em Português apenas para agilizar o entendimento de de avaliação
    - Os endpoints da API podem ser consultados no Swagger, bem como estarão nas imagens abaixo.

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- RabbitMQ

# Links Uteis
    Projeto no Github:  https://github.com/naicson88/desafio-sicredi;
    Url base Heroku:   http://des-sic-app.herokuapp.com/
    Swagger da API:    https://des-sic-app.herokuapp.com/swagger-ui.html
    Cloud AMQP:   jackal.rmq.cloudamqp.com (dados de acesso no Application.Properties caso queiram avaliar a Queue)

## Implantação em produção
- Cloud: Heroku
- Banco de dados: MySQL

# Endpoints

    POST – Cadastrar Pauta: api/v1/pauta/cadastrar-pauta
    POST – Criar Sessao: api/v1/sessao/criar-sessao-votacao
    POST - Votar: /api/v1/voto/votar
    GET - Consultar resultado: api/v1/voto/consultar-resultado-votacao?pautaId=?


# Como executar o projeto
![uso 1](https://github.com/naicson88/desafio-sicredi/blob/master/assets/ex1.png)
![uso 2](https://github.com/naicson88/desafio-sicredi/blob/master/assets/ex2.png)
![uso 3](https://github.com/naicson88/desafio-sicredi/blob/master/assets/ex3.png)
![uso 4](https://github.com/naicson88/desafio-sicredi/blob/master/assets/ex4.png)


# Autor

Alan Naicson Costa cunha

https://www.linkedin.com/in/naicson88/

