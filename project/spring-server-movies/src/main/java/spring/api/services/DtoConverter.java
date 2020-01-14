package spring.api.services;

import org.springframework.stereotype.Component;
import spring.entities.ActorEntity;
import spring.entities.MovieEntity;
import spring.model.Actor;
import spring.model.Movie;

@Component
public class DtoConverter {

    public Movie toMovie(MovieEntity movieEntity) {
        Movie movie = new Movie();
        movie.setTitle(movieEntity.getTitle());
        movie.setDirector(movieEntity.getDirector());
        movie.setStudio(movieEntity.getStudio());
        movie.setProduction(movieEntity.getProduction());
        movie.setRating(movieEntity.getRating());
        movie.setRevenue(movieEntity.getRevenue());
        return movie;
    }

    public MovieEntity toMovieEntity(Movie movie) {
        return MovieEntity.builder().title(movie.getTitle())
                .studio(movie.getStudio())
                .rating(movie.getRating())
                .production(movie.getProduction())
                .director(movie.getDirector())
                .revenue(movie.getRevenue())
                .build();
    }

    public Actor toActor(ActorEntity actorEntity) {
        Actor actor = new Actor();
        actor.setFirstname(actorEntity.getFirstname());
        actor.setLastname(actorEntity.getLastname());
        switch (actorEntity.getExpertise()) {
            case FILM:
                actor.setExpertise(Actor.ExpertiseEnum.FILM);
                break;
            case TELEVISION:
                actor.setExpertise(Actor.ExpertiseEnum.TELEVISION);
                break;
            case THEATER:
                actor.setExpertise(Actor.ExpertiseEnum.THEATER);
                break;
        }
        return actor;
    }

    public ActorEntity toActorEntity(Actor actor) {
        ActorEntity actorEntity = new ActorEntity();

        actorEntity.setFirstname(actor.getFirstname());
        actorEntity.setLastname(actor.getLastname());
        switch (actor.getExpertise()) {
            case FILM:
                actorEntity.setExpertise(ActorEntity.ExpertiseEnum.FILM);
                break;
            case TELEVISION:
                actorEntity.setExpertise(ActorEntity.ExpertiseEnum.TELEVISION);
                break;
            case THEATER:
                actorEntity.setExpertise(ActorEntity.ExpertiseEnum.THEATER);
                break;
        }

        return actorEntity;
    }
}
