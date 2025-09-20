# Blog API em Spring Boot
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white) ![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black) ![Insomnia](https://img.shields.io/badge/Insomnia-black?style=for-the-badge&logo=insomnia&logoColor=5849BE)

Blog API desenvolvida em Java utilizando Spring Boot Framework e MySQL, seguindo as melhores práticas de desenvolvimento e arquitetura.

## 🚀 Tecnologias e Ferramentas

- **Java 17** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework para desenvolvimento Back-End
- **Spring Security** - Segurança e autenticação
- **Spring Data JPA** - Framework para acesso a dados com ORM
- **Spring Validation** - Validação de dados
- **Spring Actuator** - Monitoramento e métricas
- **Lombok** - Redução de código boilerplate
- **MapStruct** - Mapeamento entre DTOs e entidades
- **MySQL** - Sistema de gerenciamento de banco de dados
- **Maven** - Gerenciamento de dependências e build
- **JUnit 5** - Framework de testes
- **Mockito** - Framework de mocking para testes

## 📁 Estrutura do Projeto

```
src/main/java/com/aronalvarenga/blog/
├── config/                 # Configurações da aplicação
│   ├── JpaConfig.java
│   ├── SecurityConfig.java
│   └── GlobalExceptionHandler.java
├── controller/             # Controladores REST
│   └── ArticleController.java
├── dto/                    # Data Transfer Objects
│   ├── ArticleRequestDto.java
│   └── ArticleResponseDto.java
├── exception/              # Exceções personalizadas
│   └── ResourceNotFoundException.java
├── mapper/                 # Mapeadores MapStruct
│   └── ArticleMapper.java
├── model/                  # Entidades JPA
│   └── Article.java
├── repository/             # Repositórios JPA
│   └── ArticleRepository.java
├── service/                # Lógica de negócio
│   └── ArticleService.java
└── BlogApiApplication.java # Classe principal
```

## 🔧 Configuração

### Pré-requisitos
- Java 17+
- Maven 3.6+
- MySQL 8.0+

### Variáveis de Ambiente
Configure as seguintes variáveis de ambiente:

```bash
# Database
export DATABASE_URL="jdbc:mysql://localhost:3306/blog_db?useTimezone=true&serverTimezone=UTC&createDatabaseIfNotExist=true"
export DATABASE_USERNAME="root"
export DATABASE_PASSWORD="sua_senha"

# Server
export SERVER_PORT="8080"
```

Para mais detalhes, consulte o arquivo [ENVIRONMENT_SETUP.md](ENVIRONMENT_SETUP.md).

## 🚀 Executando a Aplicação

### Desenvolvimento
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Produção
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## 📚 Endpoints da API

### Endpoints Públicos
- `GET /api/v1/articles/published` - Lista artigos publicados
- `GET /api/v1/articles/published/paginated` - Lista artigos publicados com paginação
- `GET /api/v1/articles/published/search?title=termo` - Busca artigos publicados por título
- `GET /api/v1/articles/{id}` - Busca artigo por ID
- `GET /actuator/health` - Health check

### Endpoints Protegidos
- `GET /api/v1/articles` - Lista todos os artigos
- `POST /api/v1/articles` - Cria novo artigo
- `PUT /api/v1/articles/{id}` - Atualiza artigo
- `DELETE /api/v1/articles/{id}` - Exclui artigo
- `PATCH /api/v1/articles/{id}/publish` - Publica artigo
- `PATCH /api/v1/articles/{id}/unpublish` - Despublica artigo

## 🧪 Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com cobertura
mvn test jacoco:report
```

## 🔒 Segurança

A aplicação implementa segurança básica com Spring Security:
- Endpoints públicos para leitura de artigos publicados
- Endpoints protegidos para operações de escrita
- Configuração preparada para implementação futura de JWT

## 📊 Monitoramento

A aplicação inclui Spring Actuator para monitoramento:
- Health checks: `/actuator/health`
- Métricas: `/actuator/metrics`
- Informações: `/actuator/info`

## 🏗️ Melhorias Implementadas

### Arquitetura
- ✅ Separação clara de responsabilidades (Controller, Service, Repository)
- ✅ Uso de DTOs para transferência de dados
- ✅ Mapeamento automático com MapStruct
- ✅ Tratamento global de exceções
- ✅ Validação de dados com Bean Validation

### Segurança
- ✅ Configuração de segurança básica
- ✅ Endpoints públicos e protegidos
- ✅ Variáveis de ambiente para dados sensíveis
- ✅ .gitignore atualizado para arquivos sensíveis

### Qualidade de Código
- ✅ Uso correto do Lombok
- ✅ Logging estruturado
- ✅ Testes unitários
- ✅ Documentação de código
- ✅ Configuração de profiles (dev/prod)

### Configuração
- ✅ Migração para YAML
- ✅ Profiles de desenvolvimento e produção
- ✅ Configuração de banco de dados otimizada
- ✅ Configuração de logging por ambiente
