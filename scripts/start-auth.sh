#!/bin/bash
cd ../
cd topology/dev
docker-compose up -d

echo Docker dev topology is now running in background

cd ../../
cd microservices/authentication/spring-server
# mvn clean install

mvn -D DB_HOST=$1 -D DB_PORT=3306 -D TOKEN_SECRET=secret -D DB_USERNAME=root -D DB_PASSWORD=admin spring-boot:run