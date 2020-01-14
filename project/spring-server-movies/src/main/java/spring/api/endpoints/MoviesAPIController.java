package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.MoviesApi;
import spring.api.services.DtoConverter;
import spring.entities.MovieEntity;
import spring.model.Movie;
import spring.repositories.MoviesRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-15T19:36:34.802Z")

@Controller
public class MoviesAPIController implements MoviesApi {

    private static final Logger log = LoggerFactory.getLogger(MoviesAPIController.class);

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<Object> createMovie(@ApiParam(value = "", required = true) @Valid @RequestBody Movie movie) {

        String owner = (String) httpServletRequest.getAttribute("owner");

        MovieEntity movieEntity = dtoConverter.toMovieEntity(movie);
        movieEntity.setOwnerId(owner);
        moviesRepository.save(movieEntity);
        Long id = movieEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(movieEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<Movie>> getMovies() {
        String requestOwner = (String) httpServletRequest.getAttribute("owner");
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : moviesRepository.findAll()) {
            if(movieEntity.getOwnerId().equals(requestOwner)) {
                movies.add(dtoConverter.toMovie(movieEntity));
            }
        }
        return ResponseEntity.ok(movies);
    }

    @Override
    public ResponseEntity<Movie> findMovieById(Long movieId) {
        Optional<MovieEntity> movieOptional = moviesRepository.findById(movieId);
        if(movieOptional.isPresent()) {
            MovieEntity movieEntity = movieOptional.get();
            String requestOwner = (String) httpServletRequest.getAttribute("owner");
            if(movieEntity.getOwnerId().equals(requestOwner)) {
                return ResponseEntity.ok(dtoConverter.toMovie(movieOptional.get()));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteMovie(Long movieId) {
        Optional<MovieEntity> movieOptional = moviesRepository.findById(movieId);
        if(movieOptional.isPresent()) {
            MovieEntity movieEntity = movieOptional.get();
            String requestOwner = (String) httpServletRequest.getAttribute("owner");
            if(movieEntity.getOwnerId().equals(requestOwner)) {
                moviesRepository.deleteById(movieId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
