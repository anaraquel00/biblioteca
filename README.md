# Biblioteca API

Esta aplicação é uma API REST desenvolvida com Spring Boot para gerenciar uma biblioteca, permitindo operações de cadastro, consulta, atualização e remoção de categorias de livros.

## Como funciona

A API expõe endpoints para manipulação de categorias, utilizando o padrão REST. Os dados são persistidos em um banco de dados relacional via Spring Data JPA. O mapeamento de objetos entre entidades e DTOs é feito com ModelMapper.

### Principais funcionalidades

- **Listar categorias:** `GET /categoria`
- **Buscar categoria por ID:** `GET /categoria/{id}`
- **Criar nova categoria:** `POST /categoria`
- **Atualizar categoria:** `PUT /categoria/{id}`
- **Remover categoria:** `DELETE /categoria/{id}`

### Como executar

1. Certifique-se de ter o Java 17+ e o Maven instalados.
2. Configure o banco de dados em `src/main/resources/application.properties`.
3. Execute o comando:

   ```sh
   mvn spring-boot:run
   ```

4. Acesse os endpoints via Postman, Insomnia ou qualquer cliente HTTP.

### Perfis

- **default:** Uso normal da aplicação.
- **test:** Inicializa o banco com dados de teste automaticamente.

### Dependências principais

- Spring Boot
- Spring Data JPA
- ModelMapper
- Banco de dados relacional (ex: MySQL, H2)

### Documentação adicional

* [Spring Boot Reference](https://docs.spring.io/spring-boot/3.5.3/reference/htmlsingle/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

---
Para dúvidas ou sugestões, Acesse:
 * [Spring Boot](https://docs.spring.io/spring-boot/3.5.3/reference/htmlsingle/)