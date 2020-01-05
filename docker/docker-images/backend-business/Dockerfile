FROM openjdk:11-slim
#
# When we build the image, we copy the executable jar in the image file system. 
#
COPY swagger-spring-movies-1.0.0.jar /opt/app/server-movies.jar

EXPOSE 8080

#
# This is the command that is executed when the Docker container starts
#
CMD ["java", "-jar", "/opt/app/server-movies.jar"]
