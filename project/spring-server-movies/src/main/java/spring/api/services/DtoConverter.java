package spring.api.services;

import org.springframework.stereotype.Component;
import spring.api.exceptions.BadRequestException;
import spring.entities.ActorEntity;
import spring.entities.MovieEntity;
import spring.entities.RoleEntity;
import spring.model.Actor;
import spring.model.Movie;
import spring.model.Role;

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

    public MovieEntity toMovieEntity(Movie movie) throws BadRequestException {
        if(movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw new BadRequestException("Movie must at least have a title");
        }
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

    /**
     * Convert a dto role to a role entity for our db. This method doesn't fetch the actor entity nor the movie entity
     * associated to the role, this should be done in the service where this method is solely used.
     * @param role the role dto
     * @return the role entity
     * @throws BadRequestException if the role dto doesn't have an actor id or a movie id
     */
    public RoleEntity toRoleEntity(Role role) throws BadRequestException {
        if(role.getActorId() == null || role.getMovieId() == null) {
            throw new BadRequestException("Roles must have an actor id and a movie id");
        }

        return RoleEntity.builder()
                .awards(role.getAwards())
                .awarded(role.getAwarded())
                .roleName(role.getRolename()).build();
    }

    /**
     * Converts a role entity to a role dto
     * @param roleEntity the role entity
     * @return the role dto
     */
    public Role toRole(RoleEntity roleEntity) {
        Role role = new Role();
        role.setActorId(roleEntity.getActorEntity().getId());
        role.setMovieId(roleEntity.getMovieEntity().getId());
        role.setAwarded(roleEntity.isAwarded());
        role.setAwards(roleEntity.getAwards());
        role.setRolename(roleEntity.getRoleName());

        return role;
    }
}
