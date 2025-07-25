# Etapa 1: Build usando Maven e Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia o projeto e resolve as dependências separadamente (melhora cache)
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Etapa 2: Runtime com Java 21 (JRE leve)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia o JAR gerado
COPY --from=builder /app/target/*.jar app.jar

# Exponha a porta usada pela aplicação
EXPOSE 8082

# Inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]