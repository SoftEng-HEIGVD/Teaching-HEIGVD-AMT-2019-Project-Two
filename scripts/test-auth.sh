#!/bin/bash
cd ../
cd microservices/authentication/fruits-specs
mvn -DskipTests clean install
mvn test