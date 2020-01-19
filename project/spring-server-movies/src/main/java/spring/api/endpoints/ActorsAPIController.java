package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import spring.api.ActorsApi;
import spring.api.ApiUtil;
import spring.api.services.ActorsService;
import spring.api.services.RolesService;
import spring.model.Actor;
import spring.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-15T19:36:34.802Z")

@Controller
public class ActorsAPIController implements ActorsApi {

    @Autowired
    ActorsService actorsService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    RolesService rolesService;

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

    @Override
    public ResponseEntity<List<Role>> getAllRolesForAnActor(@ApiParam(value = "Actor id",required=true) @PathVariable("actorId") Long actorId) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"actorId\" : 0, \"awarded\" : true, \"rolename\" : \"rolename\", \"awards\" : 1, \"movieId\" : 6 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        String owner = (String) httpServletRequest.getAttribute("owner");
        return ResponseEntity.ok(rolesService.getAllRolesByActor(actorId, owner));
    }

    @Override
    public ResponseEntity<Void> updateActor(@ApiParam(value = "ID of actor to fetch",required=true) @PathVariable("actorId") Long actorId,@ApiParam(value = "the updated actor." ,required=true )  @Valid @RequestBody Actor updatedActor) throws Exception {
        String owner = (String) httpServletRequest.getAttribute("owner");
        actorsService.updateActor(actorId, updatedActor, owner);
        return ResponseEntity.ok().build();
    }
}
