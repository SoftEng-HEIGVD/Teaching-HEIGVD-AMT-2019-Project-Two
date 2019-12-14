package ch.heig.amt.pokemon.api.endpoints;

import ch.heig.amt.pokemon.api.ApiUtil;
import ch.heig.amt.pokemon.api.PokemonsApi;
import ch.heig.amt.pokemon.api.model.Pokemon;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PokemonsApiControllers implements PokemonsApi {

    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "createPokemon", notes = "create a pokemon", response = Pokemon.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "created", response = Pokemon.class) })
    @RequestMapping(value = "/pokemons",
            produces = { "*/*" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<Pokemon> createPokemon(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Pokemon pokemon) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("*/*"))) {
                    String exampleString = "{ \"pokedexId\" : 0, \"name\" : \"name\", \"hp\" : 1, \"type\" : \"type\", \"category\" : \"category\", \"height\" : 6 }";
                    ApiUtil.setExampleResponse(request, "*/*", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "", nickname = "deletePokemonByID", notes = "delete a pokemon by its ID", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "sucessfully deleted") })
    @RequestMapping(value = "/pokemons/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePokemonByID(@ApiParam(value = "The pokemon ID",required=true) @PathVariable("id") Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "deletePokemons", notes = "delete the list of all pokemons", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "sucessfully deleted") })
    @RequestMapping(value = "/pokemons",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePokemons(@ApiParam(value = "name of the pokemon to delete") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "type of the pokemon to delete") @Valid @RequestParam(value = "type", required = false) String type,@ApiParam(value = "category of the pokemon to delete") @Valid @RequestParam(value = "category", required = false) String category,@ApiParam(value = "height of the pokemon to delete") @Valid @RequestParam(value = "height", required = false) Integer height,@ApiParam(value = "hp of the pokemon to delete") @Valid @RequestParam(value = "hp", required = false) Integer hp) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getPokemonByID", notes = "get a pokemon by its ID", response = Pokemon.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Pokemon.class) })
    @RequestMapping(value = "/pokemons/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<Pokemon> getPokemonByID(@ApiParam(value = "The pokemon ID",required=true) @PathVariable("id") Integer id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"pokedexId\" : 0, \"name\" : \"name\", \"hp\" : 1, \"type\" : \"type\", \"category\" : \"category\", \"height\" : 6 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getPokemons", notes = "get the list of all pokemons", response = Pokemon.class, responseContainer = "List", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = Pokemon.class, responseContainer = "List") })
    @RequestMapping(value = "/pokemons",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<List<Pokemon>> getPokemons(@ApiParam(value = "name of the pokemon to return") @Valid @RequestParam(value = "name", required = false) String name, @ApiParam(value = "type of the pokemon to return") @Valid @RequestParam(value = "type", required = false) String type, @ApiParam(value = "category of the pokemon to return") @Valid @RequestParam(value = "category", required = false) String category, @ApiParam(value = "height of the pokemon to return") @Valid @RequestParam(value = "height", required = false) Integer height, @ApiParam(value = "hp of the pokemon to return") @Valid @RequestParam(value = "hp", required = false) Integer hp) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"pokedexId\" : 0, \"name\" : \"name\", \"hp\" : 1, \"type\" : \"type\", \"category\" : \"category\", \"height\" : 6 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "updatePokemonByID", notes = "update a pokemon by its ID", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success") })
    @RequestMapping(value = "/pokemons/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePokemonByID(@ApiParam(value = "The pokemon ID",required=true) @PathVariable("id") Integer id,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Pokemon pokemon) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
