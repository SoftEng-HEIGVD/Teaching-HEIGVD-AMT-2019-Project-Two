FROM openjdk:11-slim
#
# When we build the image, we copy the executable jar in the image file system. 
#
COPY swagger-spring-users-1.0.0.jar /opt/app/server-users.jar

COPY wait-for-it.sh /

EXPOSE 6060