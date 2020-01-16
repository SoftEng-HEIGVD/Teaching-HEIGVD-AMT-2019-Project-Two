package spring.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.exceptions.ForbiddenException;
import spring.api.exceptions.NotFoundException;
import spring.entities.MovieEntity;
import spring.model.Movie;
import spring.repositories.MoviesRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    DtoConverter dtoConverter;

    @Override
    public URI saveMovie(Movie movie, String ownerID) {
        MovieEntity movieEntity = dtoConverter.toMovieEntity(movie);
        movieEntity.setOwnerId(ownerID);
        moviesRepository.save(movieEntity);
        Long id = movieEntity.getId();

        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(movieEntity.getId()).toUri();
    }

    public List<Movie> findMoviesByUser(String ownerId) throws NotFoundException {
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : moviesRepository.findAll()) {
            if(movieEntity.getOwnerId().equals(ownerId)) {
                movies.add(dtoConverter.toMovie(movieEntity));
            }
        }

        if (movies.isEmpty()) {
            throw new NotFoundException("User doesn't own any movie yet");
        }
        return movies;
    }

    @Override
    public void deleteMovie(Long movieId, String requestOwner) throws NotFoundException, ForbiddenException {
        MovieEntity movieEntity = moviesRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException("Could not find movie with movie id " + movieId));

        if (!movieEntity.getOwnerId().equals(requestOwner)) {
            throw new ForbiddenException("Cannot delete another's user movie");
        }

        moviesRepository.deleteById(movieId);
    }

    @Override
    public Movie findMovieById(Long movieId, String requestOwner) throws NotFoundException, ForbiddenException {

        MovieEntity movieEntity = moviesRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException("Could not find movie with movie id " + movieId));

        if (!movieEntity.getOwnerId().equals(requestOwner)) {
            throw new ForbiddenException("Cannot retrieve another's user movie");
        }

        return dtoConverter.toMovie(movieEntity);
    }
}
