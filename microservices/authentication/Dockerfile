FROM maven:3.6.3-jdk-8 AS build
COPY spring-server/ /usr/spring-server 
RUN mvn -f /usr/spring-server/pom.xml clean package

FROM openjdk:8-jdk-alpine 
RUN apk update && apk add bash
COPY ./wait-for-it.sh /usr/scripts/
COPY --from=build /usr/spring-server/target/swagger-spring-1.0.0.jar /usr/app/myjar.jar  
EXPOSE 8081  
CMD ["java","-jar","/usr/app/myjar.jar"]  