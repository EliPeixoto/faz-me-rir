diff --git a/README.md b/README.md
index fb2bc66f277b5d5f1295fb8004b3fcaadee6d59c..2893e9f7451312aa69dc2e358d3c17ef7a835c4d 100644
--- a/README.md
+++ b/README.md
@@ -1 +1,44 @@
-# faz-me-rir
+# Faz Me Rir
+
+Projeto de exemplo em **Spring Boot** para controle financeiro simples. A API permite gerenciar receitas, despesas e saldo, persistindo os dados em um banco PostgreSQL.
+
+## Build
+
+```bash
+mvn clean package
+```
+O jar será gerado em `target/`.
+
+## Execução
+
+1. Suba o banco de dados com o *docker-compose*:
+   ```bash
+   docker-compose up -d
+   ```
+2. Execute a aplicação localmente:
+   ```bash
+   java -jar target/fazmerir-0.0.1-SNAPSHOT.jar
+   ```
+   ou construa a imagem Docker e execute:
+   ```bash
+   docker build -t fazmerir .
+   docker run --rm -p 8080:8080 fazmerir
+   ```
+
+## Endpoints principais
+
+- `POST /auth/login` – login simples simulando token.
+- `GET /receitas` – lista todas as receitas.
+- `GET /receitas/{id}` – busca receita por id.
+- `POST /receitas` – cadastra nova receita.
+- `PUT /receitas/{id}` – atualiza receita.
+- `PUT /receitas/altera-status/{id}` – altera status da receita.
+- `DELETE /receitas/{id}` – remove receita.
+- `GET /receitas/soma-recebido` – soma receitas recebidas.
+- `GET /despesas` – lista todas as despesas.
+- `POST /despesas` – cadastra nova despesa.
+- `PUT /despesas/altera-status/{id}` – altera status de despesa.
+- `GET /despesas/soma-pago` – soma despesas pagas.
+- `GET /saldo` – consulta o saldo atual.
+- `POST /saldo` – cadastra saldo inicial.
+
