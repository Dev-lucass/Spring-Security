# README V3

# Social-Login (Spring Boot)

Projeto Spring Boot simples que oferece **login social via Google (OAuth2)**.

> Observação: este README pressupõe que a aplicação é executada localmente em http://localhost:8080 e que você usará as variáveis de ambiente CLIENT_ID e CLIENT_SECRET (configuradas no IntelliJ ou no ambiente) — veja a seção Configuração.
> 

---

## Sumário

- ✅ Descrição
- 🧩 Pré-requisitos
- 🔧 Configuração no Google Cloud Platform (GCP)
- ⚙️ Configuração do projeto (IntelliJ / variáveis de ambiente)
- ▶️ Como rodar
- 🧪 Testando o login
- 🐞 Troubleshooting (erros comuns)
- 📄 Exemplo de `application.yml`

---

## Descrição

Aplicação Spring Boot que usa apenas o fluxo de OAuth2 do Google para autenticação social. A página de login padrão da aplicação fica em:

```
http://localhost:8080/login

```

Quando o usuário clicar em "Sign in with Google" (ou ação equivalente), o fluxo OAuth2 do Google será usado. A aplicação obtém `client-id` e `client-secret` via variáveis de ambiente.

---

## Pré-requisitos

- Java 17+ (ou versão compatível com seu `spring-boot`)
- Maven ou Gradle (conforme seu projeto)
- Conta no Google Cloud Platform (GCP)
- IntelliJ IDEA (ou outra IDE) para configurar variáveis de ambiente localmente

---

## Configuração no Google Cloud Platform (GCP)

1. Acesse o Console do GCP e **crie um novo projeto** (ou escolha um existente).
2. No menu lateral procure por **APIs & Services > OAuth consent screen** e configure a tela de consentimento:

- Tipo de usuário: `External` (para testes locais normalmente funciona) ou `Internal` (somente para contas organizacionais).
- Preencha: nome do app, e-mail de suporte, e demais campos obrigatórios.

1. Vá em **APIs & Services > Credentials** e clique em **Create Credentials > OAuth client ID**.
    - Application type: **Web application**.
    - Name: `spring-boot-local` (ou como preferir).
    - **Authorized JavaScript origins**: adicione
        
        ```
        http://localhost:8080
        
        ```
        
    - **Authorized redirect URIs**: adicione a URI de redirecionamento usada pelo Spring Security:
        
        ```
        http://localhost:8080/login/oauth2/code/google
        
        ```
        
    
    > Observação: o padrão do Spring Security (Spring Boot com spring-boot-starter-oauth2-client) para o redirect é /{base}/login/oauth2/code/{registrationId} — para o Google o {registrationId} costuma ser google, portanto a URI completa é http://localhost:8080/login/oauth2/code/google. Se você tiver sobrescrito a propriedade redirect-uri no application.yml, registre exatamente essa URI no GCP.
    > 
    

1. Após criar, copie o **Client ID** e **Client secret**. Eles serão utilizados como variáveis de ambiente `CLIENT_ID` e `CLIENT_SECRET`.

---

## Configuração do projeto (IntelliJ / variáveis de ambiente)

### Exemplo: `application.yml` (utilizando placeholders de ambiente)

```yaml
spring:
  application:
    name: Social-Login
  
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${CLIENT_ID}
            client-secret: ${CLIENT_SECRET}

```

> O Spring Boot resolve ${CLIENT_ID} e ${CLIENT_SECRET} a partir das variáveis de ambiente do sistema ou das variáveis definidas na configuração de execução do IntelliJ.
> 

### Como configurar variáveis no IntelliJ (Run/Debug configuration)

1. `Run > Edit Configurations...`.
2. Selecione sua configuração de execução (ou crie uma `Application` config).
3. No campo **Environment variables** adicione:

```
CLIENT_ID=seu-client-id-aqui;CLIENT_SECRET=seu-client-secret-aqui

```

---

## Como rodar

1. Certifique-se que `CLIENT_ID` e `CLIENT_SECRET` estão configurados (intellij ou env).
2. Rode a aplicação pela IDE ou:

```bash
./mvnw spring-boot:run
# ou
./gradlew bootRun

```

1. Acesse:

```
http://localhost:8080/login

```

Clique no botão de login com Google — você será redirecionado para o consent screen do Google e, após permitir, retornará para a aplicação autenticada.

---

## Troubleshooting (erros comuns)

- **redirect_uri_mismatch**
    - Normalmente significa que a URI registrada no GCP não bate com a URI enviada pela aplicação. Verifique a URI exata que o Spring está usando (por padrão `http://localhost:8080/login/oauth2/code/google`) e registre exatamente a mesma no GCP.
- **invalid_client** / **unauthorized_client**
    - Verifique se o `CLIENT_ID`/`CLIENT_SECRET` estão corretos e foram configurados como variáveis de ambiente antes de iniciar a aplicação.
- **CORS / JavaScript origin**
    - Se você estiver usando chamadas frontend puras que chamam APIs, confirme se `Authorized JavaScript origins` inclui `http://localhost:8080`.

---

## Dicas rápidas

- Se quiser ver exatamente a URI que sua aplicação envia, ative logs do Spring Security (logger `org.springframework.security.oauth2.client` para `DEBUG`).
- Em produção use domínios seguros (`https`) e registre os domínios/URIs corretos no GCP.

---