FROM openjdk:19-jdk-alpine

WORKDIR /app

COPY src/main/resources/files /app/src/main/resources/files
COPY target/MortgageApplication-0.0.1-SNAPSHOT.jar /app/mortgage.jar

ENTRYPOINT ["java", "-jar", "/app/mortgage.jar"]
