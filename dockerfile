# Usa uma imagem Java oficial
FROM openjdk:21-jdk-slim

# Diretório de trabalho no container
WORKDIR /app

# Copia o jar gerado para dentro do container
COPY target/*.jar app.jar

# Expõe a porta que a aplicação vai usar
EXPOSE 8080

# Comando para rodar o projeto
ENTRYPOINT ["java", "-jar", "app.jar"]
