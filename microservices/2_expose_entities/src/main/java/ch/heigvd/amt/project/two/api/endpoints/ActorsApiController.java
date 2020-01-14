package ch.heigvd.amt.project.two.api.endpoints;

import ch.heigvd.amt.project.two.api.ActorsApi;
import ch.heigvd.amt.project.two.entities.ActorEntity;
import ch.heigvd.amt.project.two.model.Actor;
import ch.heigvd.amt.project.two.repositories.ActorRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ActorsApiController implements ActorsApi {

    @Autowired
    ActorRepository actorRepository;

    public ResponseEntity<Object> createActor(@ApiParam(value = "", required = true) @Valid @RequestBody Actor actor) {
        ActorEntity newActorEntity = toActorEntity(actor);
        actorRepository.save(newActorEntity);
        Long id = newActorEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newActorEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<List<Actor>> getActor() {
        List<Actor> actors = new ArrayList<>();
        for (ActorEntity actorEntity : actorRepository.findAll()) {
            actors.add(toActor(actorEntity));
        }

        return ResponseEntity.ok(actors);
    }


    private ActorEntity toActorEntity(Actor actor) {
        ActorEntity entity = new ActorEntity();
        entity.setEmail(actor.getEmail());
        entity.setFullname(actor.getFullname());
        return entity;
    }

    private Actor toActor(ActorEntity entity) {
        Actor actor = new Actor();
        entity.setEmail(actor.getEmail());
        entity.setFullname(actor.getFullname());
        return actor;
    }

}
