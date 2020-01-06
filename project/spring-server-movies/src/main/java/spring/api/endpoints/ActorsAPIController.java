package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.ActorsApi;
import spring.entities.ActorEntity;
import spring.model.Actor;
import spring.repositories.ActorsRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-15T19:36:34.802Z")

@Controller
public class ActorsAPIController implements ActorsApi {

    @Autowired
    ActorsRepository actorsRepository;

    @Override
    public ResponseEntity<Object> createActor(@ApiParam(value = "", required = true) @Valid @RequestBody Actor actor) {
        ActorEntity actorEntity = toActorEntity(actor);
        actorsRepository.save(actorEntity);
        Long id = actorEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(actorEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteActor(Long actorId) {
        // TODO: Do we need to check first
        actorsRepository.deleteById(actorId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Actor> findActorById(Long actorId) {
        Optional<ActorEntity> actorOptional = actorsRepository.findById(actorId);
        if(actorOptional.isPresent()) {
            return ResponseEntity.ok(toActor(actorOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Actor>> getActors() {
        List<Actor> actors = new ArrayList<>();
        for (ActorEntity actorEntity : actorsRepository.findAll()) {
            actors.add(toActor(actorEntity));
        }
        return ResponseEntity.ok(actors);
    }

    private Actor toActor(ActorEntity actorEntity) {
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

    private ActorEntity toActorEntity(Actor actor) {
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
}
