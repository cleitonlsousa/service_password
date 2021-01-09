# Service Password
API para validação de password com Java e Spring Boot.

### Detalhes da API

A API do service password contém as seguintes características:

 - Projeto criado com Spring Boot, Java 8 e Maven
 - Testes unitários e de integração com JUnit e Mockito

A API utiliza as regras abaixo para validação do password:
  - Nove ou mais caracteres
  - Ao menos 1 dígito
  - Ao menos 1 letra minúscula
  - Ao menos 1 letra maiúscula
  - Ao menos 1 caractere especial
  - Considere como especial os seguintes caracteres: !@#$%^&*()-+
  - Não possuir caracteres repetidos dentro do conjunto

### Como executar a aplicação
Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git.

- `git clone https://github.com/cleitonlsousa/service_password.git`
- `cd service_password`
- `mvn spring-boot:run`
- `Acesse o endpoint através da url localhost:8080/password/validate`
- `A chamada utiliza o metodo POST e deve enviar o corpo: {"password" : "password"}`

### Importando o projeto

O projeto pode ser importado no eclipse ou Intellij como um projeto maven. Não é necessária nenhuma configuração
adicional para executar o projeto apenas download das dependências.