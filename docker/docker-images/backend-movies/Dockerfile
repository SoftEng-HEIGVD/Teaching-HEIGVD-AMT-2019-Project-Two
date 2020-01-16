FROM openjdk:11-slim
#
# When we build the image, we copy the executable jar in the image file system. 
#
COPY swagger-spring-movies-1.0.0.jar /opt/app/server-movies.jar

COPY wait-for-it.sh /

EXPOSE 8080
