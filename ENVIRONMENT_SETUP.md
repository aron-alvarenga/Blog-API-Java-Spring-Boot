# Configuração de Ambiente - Blog API

## Variáveis de Ambiente Necessárias

Para executar a aplicação, você precisa configurar as seguintes variáveis de ambiente:

### Desenvolvimento (application-dev.yml)
```bash
# Database Configuration
export DATABASE_URL="jdbc:mysql://localhost:3306/blog_db?useTimezone=true&serverTimezone=UTC&createDatabaseIfNotExist=true"
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

### Desenvolvimento
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Produção
```bash
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
