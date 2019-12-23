package ch.heig.amt.pokemon.api.endpoints;

import ch.heig.amt.pokemon.api.ApiUtil;
import ch.heig.amt.pokemon.api.PokemonsApi;
import ch.heig.amt.pokemon.api.model.Pokemon;
import ch.heig.amt.pokemon.entities.PokemonEntity;
import ch.heig.amt.pokemon.repositories.PokemonRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
public class PokemonsApiControllers implements PokemonsApi {

    @Autowired
    private PokemonRepository pokemonRepository;

    /*
       URL : /pokemons
       method : POST with JSON
       Be careful to add Accept : application/json in header request
       may be implement in a TODO
     */
    public ResponseEntity<Pokemon> createPokemon(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Pokemon pokemon) {
        PokemonEntity pokemonEntity = toEntity(pokemon);

        PokemonEntity createdPokemonEntity = pokemonRepository.save(pokemonEntity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/pokemons/{id}").buildAndExpand(createdPokemonEntity.getPokeDexId()).toUri();

        return ResponseEntity.created(uri).body(toPokemon(createdPokemonEntity));
    }

    /*
       URL : /pokemons/{id}
       method : DELETE
     */
    public ResponseEntity<Void> deletePokemonByID(@ApiParam(value = "The pokemon ID",required=true) @PathVariable("id") Integer id) {
        pokemonRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @ApiOperation(value = "", nickname = "deletePokemons", notes = "delete the list of all pokemons", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "sucessfully deleted") })
    @RequestMapping(value = "/pokemons",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePokemons(@ApiParam(value = "name of the pokemon to delete") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "type of the pokemon to delete") @Valid @RequestParam(value = "type", required = false) String type,@ApiParam(value = "category of the pokemon to delete") @Valid @RequestParam(value = "category", required = false) String category,@ApiParam(value = "height of the pokemon to delete") @Valid @RequestParam(value = "height", required = false) Integer height,@ApiParam(value = "hp of the pokemon to delete") @Valid @RequestParam(value = "hp", required = false) Integer hp) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /*
       URL : /pokemons/{id}
       method : GET
     */
    public ResponseEntity<Pokemon> getPokemonByID(@ApiParam(value = "The pokemon ID",required=true) @PathVariable("id") Integer id) {
        Optional<PokemonEntity> optionalPokemonEntity = pokemonRepository.findBypokeDexId(id);

        PokemonEntity pokemonEntity = optionalPokemonEntity.get();

        return ResponseEntity.ok(toPokemon(pokemonEntity));
    }

    /*
       URL : /pokemons
       method : GET
     */
    public ResponseEntity<List<Pokemon>> getPokemons(@ApiParam(value = "name of the pokemon to return") @Valid @RequestParam(value = "name", required = false) String name, @ApiParam(value = "type of the pokemon to return") @Valid @RequestParam(value = "type", required = false) String type, @ApiParam(value = "category of the pokemon to return") @Valid @RequestParam(value = "category", required = false) String category, @ApiParam(value = "height of the pokemon to return") @Valid @RequestParam(value = "height", required = false) Integer height, @ApiParam(value = "hp of the pokemon to return") @Valid @RequestParam(value = "hp", required = false) Integer hp) {
        List<Pokemon> pokemons = new ArrayList<>();
        List<PokemonEntity> pokemonsEntities = new ArrayList<>();

        pokemonsEntities = (List<PokemonEntity>) pokemonRepository.findAll();

        for(PokemonEntity pokemonEntity : pokemonsEntities) {
            pokemons.add(toPokemon(pokemonEntity));
        }

        return ResponseEntity.ok(pokemons);
    }


    @ApiOperation(value = "", nickname = "updatePokemonByID", notes = "update a pokemon by its ID", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success") })
    @RequestMapping(value = "/pokemons/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePokemonByID(@ApiParam(value = "The pokemon ID",required=true) @PathVariable("id") Integer id,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Pokemon pokemon) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /* Entity to POJO conversion */
    private PokemonEntity toEntity(Pokemon pokemon) {
        PokemonEntity pokemonEntity = new PokemonEntity();

        pokemonEntity.setPokeDexId(pokemon.getPokedexId());
        pokemonEntity.setName(pokemon.getName());
        pokemonEntity.setCategory(pokemon.getCategory());
        pokemonEntity.setHeight(pokemon.getHeight());
        pokemonEntity.setHp(pokemon.getHp());
        pokemonEntity.setType(pokemon.getType());

        return pokemonEntity;
    }

    /* POJO to Entity conversion */
    private Pokemon toPokemon(PokemonEntity pokemonEntity) {
        Pokemon pokemon = new Pokemon();

        pokemon.setPokedexId(pokemonEntity.getPokeDexId());
        pokemon.setName(pokemonEntity.getName());
        pokemon.setCategory(pokemonEntity.getCategory());
        pokemon.setHeight(pokemonEntity.getHeight());
        pokemon.setHp(pokemonEntity.getHp());
        pokemon.setType(pokemonEntity.getType());

        return pokemon;
    }
}
