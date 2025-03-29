# Sistema de Votação

Este é um sistema de votação construído com Spring Boot, que permite a criação de pautas de votação, a abertura de sessões de votação e o registro de votos. Ele foi desenvolvido como  demonstração de como construir APIs RESTful com integração de banco de dados, utilizando boas práticas de arquitetura e design de software.

## Funcionalidades

- **Criar Pauta**: Permite criar uma nova pauta de votação.
- **Abrir Sessão de Votação**: A partir de uma pauta existente, é possível abrir uma nova sessão de votação, onde o usuário pode definir a duração da sessão.
- **Votar**: Os usuários podem votar em uma pauta, registrando votos a favor ou contra.
- **Validação**: O sistema valida se a sessão de votação está ativa e se o usuário já votou.

## Tecnologias

Este projeto utiliza as seguintes tecnologias:

- **Spring Boot**: Framework principal para a construção da API RESTful.
- **H2 Database**: Banco de dados em memória utilizado para persistência dos dados durante o desenvolvimento e testes.
- **Swagger**: Para a documentação da API.
- **JUnit 5**: Para a execução dos testes automatizados.
- **Maven**: Gerenciador de dependências e construção do projeto.

## Instalação

### Pré-requisitos

- Java 11 ou superior.
- Maven 3.6 ou superior.

### Passos para execução

1. Clone este repositório:

    ```bash
    git clone https://github.com/seu-usuario/nome-do-repositorio.git
    ```

2. Navegue até a pasta do projeto:

    ```bash
    cd nome-do-repositorio
    ```

3. Execute o seguinte comando para compilar e rodar a aplicação:

    ```bash
    mvn spring-boot:run
    ```

A aplicação estará rodando em `http://localhost:8080`.

## Testes

Este projeto inclui testes automatizados utilizando JUnit. Para rodar os testes, execute o seguinte comando:

```bash
mvn test
