# Teaching-HEIGVD-AMT-2019-Project-Two

> François Burgener, Tiago Povoa Q.

## In short

Implementation of 2 Restful API using Spring Boot, Swagger, JWT and Cucumber

## Introduction

### Quick Start

To run this project, first go to `topology/prod`. 

You'll have to add env variables. For example by adding `.env` file like the following:

```
ROOT_PASSWORD=admin
MYSQL_DATABASE_AUTH=authentication_api
MYSQL_DATABASE_TRAVEL=travel_api

DB_PORT=3306
TOKEN_SECRET=secret

DB_USERNAME=root
```

Then start it with `docker-compose up`. It will run a few containers:

* Traefik that we use as a dynamic reverse proxy. Don't mind the funny gophers in here.
* Two Spring APIs: Auth and Travel
* Two MySQL Databases
* A phpmyadmin container so you can inspect data

## Business domain

Todo

## Implementation

Todo

## Testing strategy

### Cucumber tests

TODO Explain the tools and the value of the tests

### Performance tests with JMeter

TODO

* number
* Graphs
* Explanations

## Known bugs and limitations

# TODO

- [ ] Hibernate: none en prod. Ajouter une env var

