## Mortgage Application

### Tools needed to successfully build and run the application
- Java 19
- Maven 3.9.6
- Docker Desktop

### How to start the app locally
- Running the app with Docker:
    - Open your Docker Desktop
  
Navigate to the root directory of the project
- Build the application by running:
    -      ./mvnw clean package
- First you need to pull the openjdk image:
    -     docker pull openjdk:19-jdk-alpine
- Build a Docker image of the application:
    -      docker build -t mortgage-application .
- Run the Docker
    -     docker run -p 8080:8080 mortgage-application

Check the website at:
http://localhost:8080/mortgage

------------------------------------------------------------
If you want you can also run the application without using docker, and use only Spring Boot:

- Build the application by running:
    -      ./mvnw clean package
- Run the jar file:
    -     java -jar target/MortgageApplication-0.0.1-SNAPSHOT.jar

Check the website at:
http://localhost:8080/mortgage

------------------------------------------------------------
Here is the link to the Web page running on AWS EC2:

http://ec2-3-67-64-3.eu-central-1.compute.amazonaws.com

