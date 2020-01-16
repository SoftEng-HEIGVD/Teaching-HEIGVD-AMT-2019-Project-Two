package spring.api.services;

import org.springframework.http.ResponseEntity;
import spring.api.exceptions.ForbiddenException;
import spring.api.exceptions.NotFoundException;
import spring.model.Movie;

import java.net.URI;
import java.util.List;

public interface MoviesService {

    /**
     * Save movie in our db with owner's id and create resource for it in our api
     * @param movie the movie
     * @param ownerID the username of the owner of the resource
     * @return the location of the movie resource created
     */
    URI saveMovie(Movie movie, String ownerID);

    /**
     * Return all movies owned by user.
     * @param ownerId username of the owner
     * @return the list of movies owned by user
     * @throws NotFoundException if no movies were for the user
     */
    List<Movie> findMoviesByUser(String ownerId) throws NotFoundException;

    /**
     * Delete a movie owned by a user.
     * @param movieId id of the movie
     * @param requestOwner username of the owner
     * @throws NotFoundException if movie with movieId was not found
     * @throws ForbiddenException if trying to delete a movie owned by another user
     */
    void deleteMovie(Long movieId, String requestOwner) throws NotFoundException, ForbiddenException;

    /**
     *
     * @param movieId id of the movie
     * @param requestOwner username of the request owner
     * @return the movie with movieId
     * @throws NotFoundException if movie with movieId was not found
     * @throws ForbiddenException if trying to get a movie owned by another user
     */
    Movie findMovieById(Long movieId, String requestOwner) throws NotFoundException, ForbiddenException;
}
