# Movies API


## The model 

Our model describes the relationship between the Actors and Movies with a Role. The Role describes the character(s)
an actor had in this movie and what awards he received for this movie. This is clearly a many to many relationship since actors can star in many movies,
and movies have many actors acting in them. <br>

In the particular case where an actor had several acting roles in a movie, we only consider it as a single instance of the
relationship Role i.e. there won't be a Role instance for every acting role an actor had in a movie but rather a single Role that
describes all the characters the actor portrayed in this movie. After all, an actor is awarded only once for a movie, and not for each portrayal he did in the movie.