
# Sistema de Votação

Este é um sistema de votação que pode ser executado localmente ou em um ambiente AWS (RDS). Ele oferece funcionalidades para criar pautas, abrir sessões de votação e permitir votos, com validação da sessão e do status do usuário. O sistema é projetado para rodar de maneira fácil em diferentes ambientes.

## Ambientes de Execução

O sistema pode ser executado de duas maneiras diferentes, com configurações para desenvolvimento local (banco H2 em memória) ou aws (banco nas nuvem), e simulando produção implantado em AWS (RDS):

### 1. **Ambiente Local**
- **Banco de dados**: H2 (em memória)
- **Variação de perfil**: `SPRING_PROFILES_ACTIVE=local,data-load`
- **Funcionalidade**: Carrega 10 pautas para testes automáticos em um banco de dados em memória.

### 2. **Ambiente AWS**
- **Banco de dados**: RDS da AWS
- **Variação de perfil**: `SPRING_PROFILES_ACTIVE=aws`
- **Funcionalidade**: Conecta-se a um banco de dados RDS na AWS.

### Links de Acesso nas nuvens (Elastic Beanstalk):
- **Ambiente de Desenvolvimento (DEV)**: [Swagger DEV](http://votacao-dev.sa-east-1.elasticbeanstalk.com/swagger-ui/index.html)
- **Ambiente de Produção (PROD)**: [Swagger PROD](http://votacao-aws-prod.sa-east-1.elasticbeanstalk.com/swagger-ui/index.html)

## Funcionalidades

- **Criar Pauta**: Permite criar uma nova pauta de votação.
- **Abrir Sessão de Votação**: A partir de uma pauta existente, é possível abrir uma nova sessão de votação, onde o usuário pode definir a duração da sessão.
- **Votação**: Usuários podem votar em uma pauta, registrando votos a favor ou contra.
- **Validação de Sessão**: O sistema valida se a sessão de votação está ativa e se o usuário já votou.

- **Funcionalidade extra (Tarefas bônus)**:  Validador de CPF para sistema externo como pede em: https://github.com/dbserver/desafio-votacao

## Tecnologias Utilizadas

Este projeto utiliza as seguintes tecnologias:

- **Java 17**: Aproveitando as novas funcionalidades e melhorias dessa versão.
- **Spring Boot 3.4.4**: Framework principal para a construção da API RESTful.
- **H2 Database**: Banco de dados em memória utilizado durante o desenvolvimento e testes.
- **Swagger**: Para documentação interativa da API.
- **JUnit 5**: Para a execução de testes automatizados.
- **Maven**: Gerenciador de dependências e construção do projeto.

## Pré-requisitos

Certifique-se de ter os seguintes requisitos instalados na sua máquina:

- **Java 17** ou superior
- **Maven 3.6** ou superior

## Passos para Execução

### 1. Clonar o Repositório

Clone o repositório para sua máquina local:

```bash
git clone https://github.com/joao210180/votacao-api.git
```

### 2. Navegar até a Pasta do Projeto

Acesse o diretório do projeto clonado:

```bash
cd nome-do-repositorio
```

### 3. Executar a Aplicação

#### **Ambiente Local com H2 (Banco de Dados em Memória)**

Para rodar a aplicação localmente com o banco H2 (em memória), execute o comando abaixo:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local,data-load
```

#### **Ambiente AWS (RDS da AWS)**

Para rodar a aplicação com o banco RDS da AWS, execute o comando abaixo:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=aws
```

A aplicação estará rodando em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## Testes

Este projeto inclui testes automatizados utilizando **JUnit**. Para rodar os testes, execute o seguinte comando:

```bash
mvn test
```

Os testes serão executados no perfil de DEV ou seja, local.


