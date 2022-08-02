FROM openjdk:8-jdk-alpine

COPY /target/e-commerce-0.0.1-SNAPSHOT.jar e-commerce-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/e-commerce-0.0.1-SNAPSHOT.jar"]