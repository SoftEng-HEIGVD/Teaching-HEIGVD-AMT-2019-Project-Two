package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.MoviesApi;
import spring.entities.MovieEntity;
import spring.model.Movie;
import spring.repositories.MoviesRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-15T19:36:34.802Z")

@Controller
public class MoviesAPIController implements MoviesApi {

    @Autowired
    MoviesRepository moviesRepository;

    @Override
    public ResponseEntity<Object> createMovie(@ApiParam(value = "", required = true) @Valid @RequestBody Movie movie) {
        MovieEntity movieEntity = toMovieEntity(movie);
        moviesRepository.save(movieEntity);
        Long id = movieEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(movieEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : moviesRepository.findAll()) {
            movies.add(toMovie(movieEntity));
        }
        return ResponseEntity.ok(movies);
    }

    @Override
    public ResponseEntity<Movie> findMovieById(Long movieId) {
        Optional<MovieEntity> movieOptional = moviesRepository.findById(movieId);
        if(movieOptional.isPresent()) {
            return ResponseEntity.ok(toMovie(movieOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteMovie(Long movieId) {
        // TODO: Do we need to check first
        moviesRepository.deleteById(movieId);
        return ResponseEntity.ok().build();
    }

    private Movie toMovie(MovieEntity movieEntity) {
        Movie movie = new Movie();
        movie.setTitle(movieEntity.getTitle());
        movie.setDirector(movieEntity.getDirector());
        movie.setStudio(movieEntity.getStudio());
        movie.setProduction(movieEntity.getProduction());
        movie.setRating(movieEntity.getRating());
        movie.setRevenue(movieEntity.getRevenue());
        return movie;
    }

    private MovieEntity toMovieEntity(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setDirector(movie.getDirector());
        movieEntity.setProduction(movie.getProduction());
        movieEntity.setRating(movie.getRating());
        movieEntity.setStudio(movie.getStudio());
        movieEntity.setRevenue(movie.getRevenue());
        movieEntity.setTitle(movie.getTitle());

        return movieEntity;
    }
}
