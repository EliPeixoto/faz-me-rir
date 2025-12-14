# Estágio 1: Build (Compilação)
# Usaremos uma imagem Java 21 com Maven para compilar seu projeto
FROM maven:3.9.5-eclipse-temurin-21 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos de build (pom.xml) para baixar dependências
# Isso otimiza o cache do Docker
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY src /app/src

# Empacota o aplicativo em um JAR executável
RUN mvn package -DskipTests

# ----------------------------------------------------------------------

# Estágio 2: Runtime (Execução)
# Usamos uma imagem base menor (slim) para o ambiente de execução final.
# Tag corrigida para JDK 21 (bullseye é uma base Debian leve)
FROM openjdk:17-jdk-slim-bullseye

# Define o argumento JAR_FILE
ARG JAR_FILE=target/*.jar

# Copia o JAR do estágio de build para o estágio de execução
COPY --from=build /app/${JAR_FILE} app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar o aplicativo JAR
ENTRYPOINT ["java","-jar","/app.jar"]
