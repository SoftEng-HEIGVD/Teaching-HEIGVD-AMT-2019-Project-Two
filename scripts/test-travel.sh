#!/bin/bash
cd ../
cd microservices/travel/fruits-specs
mvn -DskipTests clean install 
mvn test