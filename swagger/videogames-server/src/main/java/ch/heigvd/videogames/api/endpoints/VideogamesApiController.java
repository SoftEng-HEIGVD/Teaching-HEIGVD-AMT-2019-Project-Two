package ch.heigvd.videogames.api.endpoints;

import ch.heigvd.videogames.api.VideogamesApi;
import ch.heigvd.videogames.entities.VideogameEntity;
import ch.heigvd.videogames.api.model.Videogame;
import ch.heigvd.videogames.repositories.VideogameRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class VideogamesApiController implements VideogamesApi {

    @Autowired
    VideogameRepository videogameRepository;

    public ResponseEntity<Object> createVideogame(@ApiParam(value = "", required = true) @Valid @RequestBody Videogame videogame) {
        VideogameEntity newVideogameEntity = toVideogameEntity(videogame);
        videogameRepository.save(newVideogameEntity);
        Long id = newVideogameEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newVideogameEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<Videogame>> getVideogames() {
        List<Videogame> videogames = new ArrayList<>();
        for (VideogameEntity videogameEntity : videogameRepository.findAll()) {
            videogames.add(toVideogame(videogameEntity));
        }

        return ResponseEntity.ok(videogames);
    }

    public ResponseEntity<Void> updateVidegame(@ApiParam(value = "Videogame object that needs to be added to the store" ,required=true )  @Valid @RequestBody Videogame videogame) {
        // To be implemented
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteVideogame(@ApiParam(value = "Videogame to delete", required=true) @PathVariable("id") long id) {
        // To be implemented
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    private VideogameEntity toVideogameEntity(Videogame videogame) {
        VideogameEntity entity = new VideogameEntity();
        entity.setKind(videogame.getKind());
        entity.setName(videogame.getName());
        entity.setSupportedOn(videogame.getSupportedOn());
        return entity;
    }

    private Videogame toVideogame(VideogameEntity entity) {
        Videogame videogame = new Videogame();
        videogame.setKind(entity.getKind());
        videogame.setName(entity.getName());
        videogame.setSupportedOn(entity.getSupportedOn());
        return videogame;
    }

}
