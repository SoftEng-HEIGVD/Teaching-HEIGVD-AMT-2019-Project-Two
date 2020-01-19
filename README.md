# Teaching-HEIGVD-AMT-2019-Project-Two

Autheurs : Nair Alic et Robel Teklehaimanot

## Objectives

The objectives of this project was to design, specify, implement and validate **2 RESTful APIs**. Namely, the goal was to use:

* **Spring Boot**, **Spring Data**, **Spring MVC** and **Spring Data** for the implementation of the endpoints and of the persistence;
* **Swagger** (**Open API**) to create a formal documentation of the REST APIs (this formal documentation has to be used in the development cycle);
* JSON Web Tokens (**JWT**) to secure the RESTful endpoints;
* **CucumberJVM** to implement BDD tests.

#### What we have implemented

We have implemented 2 APIs. One is managing the users and the second one we can manage our products (videogames store). In the user API, there are 2 types of user. Admin and normal user. Admin user can manage all the other users.

The second API a user can manage videogames to his store.

Our APIs are running in Docker containers, to start them check the link on how to do below. We also used Traefik as a dynamic reverse-proxy. Our databases are running on MySQL.

Some BDD tests are made using CucumberJVM, check below links.

#### How we have implemented

We have first of all used Swagger-Editor to have a high-level view of our APIs (from a specification). It's top-down methodology as seen in the course. So we started with an already generated skeleton of code. Then we updated the skeleton classes as we needed. We implemented the JWT token for the user authentication. We used Interceptor in order to check if a user is logged, an admin, etc. Very useful.

A user can only see his videogames. An admin can manage all other users.

## Links

- Follow [this link](./doc/README_deployment.md) in order to **deploy** the web application on your machine.
- Follow [this link](./doc/README_usability.md) to know how to use the web application
- Follow [this link](./doc/README_tests.md) if you want to see **the tests** we've made.
- Follow [this link](./doc/README_bugs_limitations.md) to see the actual knows bugs and limitations.