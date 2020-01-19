package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import spring.api.ApiUtil;
import spring.api.MoviesApi;
import spring.api.services.MoviesService;
import spring.api.services.RolesService;
import spring.model.Movie;
import spring.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-15T19:36:34.802Z")

@Controller
public class MoviesAPIController implements MoviesApi {

    private static final Logger log = LoggerFactory.getLogger(MoviesAPIController.class);

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    MoviesService moviesService;

    @Autowired
    RolesService rolesService;

    @Override
    public ResponseEntity<Object> createMovie(@ApiParam(value = "Created movie object" ,required=true )  @Valid @RequestBody Movie movie) throws Exception {
        String owner = (String) httpServletRequest.getAttribute("owner");
        return ResponseEntity.created(moviesService.saveMovie(movie, owner)).build();
    }

    @Override
    public ResponseEntity<List<Movie>> getMovies(@Min(0)@ApiParam(value = "The number of the displayed page.", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page, @Min(0) @Max(100) @ApiParam(value = "The number of items to be displayed on one page.", defaultValue = "20") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"studio\" : \"studio\", \"revenue\" : 6.027456183070403, \"production\" : 0.8008281904610115, \"director\" : \"director\", \"rating\" : 1.4658129805029452, \"title\" : \"title\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        String owner = (String) httpServletRequest.getAttribute("owner");
        return ResponseEntity.ok(moviesService.findMoviesByUser(owner, page, pageSize));
    }

    @Override
    public ResponseEntity<Void> deleteMovie(@ApiParam(value = "the id of the movie to delete",required=true) @PathVariable("movieId") Long movieId) throws Exception {
        String requestOwner = (String) httpServletRequest.getAttribute("owner");
        moviesService.deleteMovie(movieId, requestOwner);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Movie> findMovieById(@ApiParam(value = "ID of the movie to fetch",required=true) @PathVariable("movieId") Long movieId) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"studio\" : \"studio\", \"revenue\" : 6.027456183070403, \"production\" : 0.8008281904610115, \"director\" : \"director\", \"rating\" : 1.4658129805029452, \"title\" : \"title\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        String requestOwner = (String) httpServletRequest.getAttribute("owner");
        return ResponseEntity.ok(moviesService.findMovieById(movieId, requestOwner));
    }

    @Override
    public ResponseEntity<List<Role>> getAllRolesForAMovie(@ApiParam(value = "Movie Id",required=true) @PathVariable("movieId") Long movieId,@Min(0)@ApiParam(value = "The number of the displayed page.", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page,@Min(0) @Max(100) @ApiParam(value = "The number of items to be displayed on one page.", defaultValue = "20") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"actorId\" : 0, \"awarded\" : true, \"rolename\" : \"rolename\", \"awards\" : 1, \"movieId\" : 6 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        String requestOwner = (String) httpServletRequest.getAttribute("owner");
        return ResponseEntity.ok(rolesService.getAllRolesByMovie(movieId, requestOwner, page, pageSize));
    }

    @Override
    public ResponseEntity<Void> updateMovie(@ApiParam(value = "ID of the movie to fetch",required=true) @PathVariable("movieId") Long movieId,@ApiParam(value = "the updated movie." ,required=true )  @Valid @RequestBody Movie updatedMovie) throws Exception {
        String requestOwner = (String) httpServletRequest.getAttribute("owner");
        moviesService.updateMovie(movieId, updatedMovie, requestOwner);
        return ResponseEntity.ok().build();
    }
}
