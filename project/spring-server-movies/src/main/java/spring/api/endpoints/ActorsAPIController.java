package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.ActorsApi;
import spring.api.ApiUtil;
import spring.api.services.ActorsService;
import spring.api.services.DtoConverter;
import spring.entities.ActorEntity;
import spring.model.Actor;
import spring.repositories.ActorsRepository;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ActorsService actorsService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<Object> createActor(@ApiParam(value = "Create actor object" ,required=true )  @Valid @RequestBody Actor actor) {
        String owner = (String) httpServletRequest.getAttribute("owner");
        return ResponseEntity.created(actorsService.saveActor(actor, owner)).build();
    }

    @Override
    public ResponseEntity<Void> deleteActor(@ApiParam(value = "Actor id to delete",required=true) @PathVariable("actorId") Long actorId) throws Exception {
        String requestOwner = (String) httpServletRequest.getAttribute("owner");
        actorsService.deleteActor(actorId, requestOwner);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Actor> findActorById(@ApiParam(value = "ID of actor to fetch",required=true) @PathVariable("actorId") Long actorId) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstname\" : \"firstname\", \"expertise\" : \"theater\", \"lastname\" : \"lastname\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        String requestOwner = (String) httpServletRequest.getAttribute("owner");
        return ResponseEntity.ok(actorsService.findActorById(actorId, requestOwner));
    }

    @Override
    public ResponseEntity<List<Actor>> getActors() throws Exception{
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstname\" : \"firstname\", \"expertise\" : \"theater\", \"lastname\" : \"lastname\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        String owner = (String) httpServletRequest.getAttribute("owner");
        return ResponseEntity.ok(actorsService.findActorsByUser(owner));
    }
}
