# Movies API


## The model 

Our model describes the relationship between the Actors and Movies with a Role. The Role describes the character(s)
an actor had in this movie and what awards he received for this movie. This is clearly a many to many relationship since actors can star in many movies,
and movies have many actors acting in them. <br>

In the particular case where an actor had several acting roles in a movie, we only consider it as a single instance of the
relationship Role i.e. there won't be a Role instance for every acting role an actor had in a movie but rather a single Role that
describes all the characters the actor portrayed in this movie. After all, an actor is awarded only once for a movie, and not for each portrayal he did in the movie.

## Useful links:
<a>https://javarevisited.blogspot.com/2017/08/difference-between-restcontroller-and-controller-annotations-spring-mvc-rest.html</a>
<a> https://medium.com/@dila.gurung/intercepting-incoming-request-using-springs-interceptor-bc1300e03f9 </a>

## TODO:
Should be able to find all movies of an actor
Should be able to find all actors in a movie
Should be able to find role of an actor in a movie (movies/1/actors/2)
castings to add role

Hot Reload

Why not Spring data REST

*how to generate custom exceptions like not authenticated 
*factor out common parameter in endpoints
*how to use swagger security schemes (in the code generated)

We disabled Unresolved database references in annotations inspection in preferences (settings)
Had to stop Apache https://askubuntu.com/questions/1074035/how-can-i-stop-apache2-on-ubuntu-18-04