# Authentication Api
Our REST api exposes 2 main APIs which act as microservices. The first of which is the authentication endpoint where one creates a user account, and use it to perform operations on the other api once he's authenticated.

We give here a brief overview of some of the implementation of this endpoint.

You can view the specificiation for this api in the project `api-user` and the implemetation of the server in `spring-server-users`. Or better, you can launch the application and view the swagger documentation on `localhost:6060/api`.

## Endpoints
Our main two endpoints in this api are implemented in a way to separate between Admin (System Administrator) and the regular user operations. All admin operations are done on `/api/users/**` paths. For this we generate the AdminApi. This endpoint obviously deals will the users resources, to which the admin has total control (except for changing passwords or updating a user's profile of course).

The other operations are implemented on other endpoints that are designed as commands, to make it easier for the user to know what he is doing. For example `/authentications` is where we can authenticate and retrieve the jwt token that we need.

## Filters
Each api commes with its own custom filters and here they are more or less alike. The important thing to remember here is JWT token. Each request to our api is intercepted in our Interceptor classes that extends the spring `HandlerInterceptorAdapter` and treat it to decide whether to process the request down the chain.

We have 2 interceptors to distinguish between the Admin and the regular user. Given 

## Controllers