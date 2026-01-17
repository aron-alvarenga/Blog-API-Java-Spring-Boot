# Configuração de Ambiente - Blog API

## Variáveis de Ambiente Necessárias

### ⚠️ IMPORTANTE: Desenvolvimento com H2

**No modo desenvolvimento, a aplicação usa H2 em memória automaticamente.** Você **NÃO precisa** configurar variáveis de ambiente para desenvolvimento. O H2 é configurado automaticamente no arquivo `application-dev.yml`.

**Console H2 disponível em:** `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:blogdb`
- Username: `sa`
- Password: (deixe em branco)

### Produção (application-prod.yml)

Para produção, você precisa configurar as seguintes variáveis de ambiente:

```bash
# Database Configuration
export DATABASE_URL="jdbc:mysql://localhost:3306/blog_db?useTimezone=true&serverTimezone=UTC"
export DATABASE_USERNAME="root"
export DATABASE_PASSWORD="sua_senha_aqui"

# Server Configuration
export SERVER_PORT="8080"
```

### Produção (application-prod.yml)
```bash
# Database Configuration
export DATABASE_URL="jdbc:mysql://seu-servidor:3306/blog_db?useTimezone=true&serverTimezone=UTC"
export DATABASE_USERNAME="usuario_producao"
export DATABASE_PASSWORD="senha_segura_producao"

# Server Configuration
export SERVER_PORT="8080"
```

## Como Configurar

### Windows (PowerShell)
```powershell
$env:DATABASE_URL="jdbc:mysql://localhost:3306/blog_db?useTimezone=true&serverTimezone=UTC&createDatabaseIfNotExist=true"
$env:DATABASE_USERNAME="root"
$env:DATABASE_PASSWORD="sua_senha_aqui"
$env:SERVER_PORT="8080"
```

### Windows (CMD)
```cmd
set DATABASE_URL=jdbc:mysql://localhost:3306/blog_db?useTimezone=true&serverTimezone=UTC&createDatabaseIfNotExist=true
set DATABASE_USERNAME=root
set DATABASE_PASSWORD=sua_senha_aqui
set SERVER_PORT=8080
```

### Linux/macOS
```bash
export DATABASE_URL="jdbc:mysql://localhost:3306/blog_db?useTimezone=true&serverTimezone=UTC&createDatabaseIfNotExist=true"
export DATABASE_USERNAME="root"
export DATABASE_PASSWORD="sua_senha_aqui"
export SERVER_PORT="8080"
```

## Executando a Aplicação

### Desenvolvimento (com H2 em memória)

**Windows (PowerShell):**
```powershell
# Opção 1: Com aspas (RECOMENDADO - obrigatório no PowerShell)
mvn spring-boot:run "-Dspring-boot.run.profiles=dev"

# Opção 2: Usando variável de ambiente
$env:SPRING_PROFILES_ACTIVE="dev"
mvn spring-boot:run

# Opção 3: Sem especificar profile (usa 'dev' por padrão do application.yml)
mvn spring-boot:run
```

**Windows (CMD):**
```cmd
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

**Linux/macOS:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

**Nota:** No modo desenvolvimento, a aplicação usa H2 em memória automaticamente. Não é necessário configurar MySQL ou variáveis de ambiente.

### Produção (com MySQL)

**Windows (PowerShell):**
```powershell
# Configure as variáveis de ambiente primeiro
$env:DATABASE_URL="jdbc:mysql://localhost:3306/blog_db?useTimezone=true&serverTimezone=UTC"
$env:DATABASE_USERNAME="root"
$env:DATABASE_PASSWORD="sua_senha_aqui"
$env:SERVER_PORT="8080"

# Execute a aplicação
mvn spring-boot:run "-Dspring-boot.run.profiles=prod"
```

**Windows (CMD):**
```cmd
set DATABASE_URL=jdbc:mysql://localhost:3306/blog_db?useTimezone=true&serverTimezone=UTC
set DATABASE_USERNAME=root
set DATABASE_PASSWORD=sua_senha_aqui
set SERVER_PORT=8080
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

**Linux/macOS:**
```bash
export DATABASE_URL="jdbc:mysql://localhost:3306/blog_db?useTimezone=true&serverTimezone=UTC"
export DATABASE_USERNAME="root"
export DATABASE_PASSWORD="sua_senha_aqui"
export SERVER_PORT="8080"
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## Segurança

- **NUNCA** commite arquivos `.env` ou com credenciais
- Use variáveis de ambiente para dados sensíveis
- Em produção, configure um gerenciador de segredos (AWS Secrets Manager, Azure Key Vault, etc.)
- Mantenha as senhas de banco de dados seguras e complexas

## Endpoints Públicos

Os seguintes endpoints são públicos (não requerem autenticação):
- `GET /api/v1/articles/published` - Lista artigos publicados
- `GET /api/v1/articles/published/paginated` - Lista artigos publicados com paginação
- `GET /api/v1/articles/published/search?title=termo` - Busca artigos publicados por título
- `GET /api/v1/articles/{id}` - Busca artigo por ID (se publicado)
- `GET /actuator/health` - Health check

## Endpoints Protegidos

Todos os outros endpoints requerem autenticação (implementação futura com JWT).

## Console H2 (Desenvolvimento)

Quando a aplicação estiver rodando em modo desenvolvimento, você pode acessar o console H2 em:

- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:blogdb`
- **Username:** `sa`
- **Password:** (deixe em branco)

O console H2 permite visualizar e gerenciar o banco de dados em memória durante o desenvolvimento.
