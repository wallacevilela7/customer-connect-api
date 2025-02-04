# **CustomerConnect** 🏢🔗

Bem-vindo ao **CustomerConnect**, um projeto prático e envolvente para dominar os fundamentos do **Spring Boot** e **Spring Data JPA**.
Este sistema de gerenciamento de clientes permite executar operações **CRUD** em uma entidade Cliente, garantindo regras de negócio bem definidas e flexibilidade na consulta de dados.

## **Funcionalidades** 🚀
- **Cadastro de Clientes**: Armazena dados cadastrais de clientes.
- **Busca Flexível**: Permite a pesquisa por CPF e/ou email, com paginação e ordenação.
- **Atualização de Dados**: Possibilidade de atualizar os dados do cliente.
- **Remoção Segura**: Deleção de registros de clientes com segurança.

## **Regras de Negócio** 📌
- **Dados obrigatórios**: Nome completo, CPF, email, telefone celular.
- **Cadastro único**: Garantia de unicidade para **ID, CPF e email**.
- **Controle de auditoria**: Registro de data de criação e atualização dos clientes automaticamente.

## **Tecnologias Utilizadas** 🛠️
- **Java 21**
- **Spring Boot 3.4.0** (Spring Web, Spring Data JPA)
- **Hibernate** (JPA)
- **Docker + MySQL** (Banco de Dados)
- **Maven**

## **Endpoints da API** 🌍

### **Criar um cliente**
- **POST** `/customers`
- **Body**:
  ```json
  {
    "fullName": "Wallace Vilela",
    "cpf": "123.456.789-00",
    "email": "wallace@email.com",
    "phoneNumber": "(11) 98765-4321"
  }
  ```
- **Response**:
  ```json
  {
    "customerId": 1,
    "fullName": "Wallace Vilela",
    "email": "wallace@email.com",
    "phoneNumber": "(11) 98765-4321"
  }
  ```

### **Buscar clientes**
- **GET** `/customers?page=0&pageSize=10&orderBy=createdAt&email=wallace@email.com`
- **Response**:
  ```json
  {
    "totalPages": 1,
    "currentPage": 0,
    "customers": [
      {
        "customerId": 1,
        "fullName": "Wallace Vilela",
        "email": "wallace@email.com",
        "phoneNumber": "(11) 98765-4321"
      }
    ]
  }
  ```

### **Atualizar cliente**
- **PUT** `/customers/{customerId}`
- **Body**:
  ```json
  {
    "fullName": "Wallace Vilela Junior",
    "email": "wallace.junior@email.com",
    "phoneNumber": "(11) 91234-5678"
  }
  ```

### **Deletar cliente**
- **DELETE** `/customers/{customerId}`
- **Response**: `204 No Content`

## **Configuração do Banco de Dados** 🗄️

## **Como Executar o Projeto** ▶️

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd customerconnect
   ```
3. Inicie o banco de dados com Docker:
   ```bash
   docker-compose up -d
   ```
4. Execute o projeto com Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Acesse a aplicação no navegador ou via Postman em: [http://localhost:8080](http://localhost:8080).

## **Contribuição** 🤝
Se desejar contribuir com melhorias ou novas funcionalidades, sinta-se à vontade para abrir uma issue ou enviar um pull request.
