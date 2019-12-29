package ch.heig.amt.pokemon.api.endpoints;

import ch.heig.amt.pokemon.api.ApiUtil;
import ch.heig.amt.pokemon.api.TrainersApi;
import ch.heig.amt.pokemon.api.model.Trainer;
import ch.heig.amt.pokemon.api.model.TrainerWithId;
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

@RestController
public class TrainersApiControllers implements TrainersApi {

    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "", nickname = "createTrainer", notes = "create a trainer", response = TrainerWithId.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "created", response = TrainerWithId.class),
            @ApiResponse(code = 400, message = "Bad request") })
    @RequestMapping(value = "/trainers",
            produces = { "*/*" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<TrainerWithId> createTrainer(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Trainer trainer) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("*/*"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "*/*", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "deleteTrainerById", notes = "delete a trainer by its ID", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "sucessfully deleted"),
            @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/trainers/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTrainerById(@ApiParam(value = "The trainer ID",required=true) @PathVariable("id") Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "deleteTrainers", notes = "delete the list of all trainers", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "sucessfully deleted") })
    @RequestMapping(value = "/trainers",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTrainers(@ApiParam(value = "name of the trainer to delete") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "surname of the trainer to delete") @Valid @RequestParam(value = "surname", required = false) String surname,@ApiParam(value = "gender of the trainer to delete") @Valid @RequestParam(value = "gender", required = false) String gender,@ApiParam(value = "age of the trainer to delete") @Valid @RequestParam(value = "age", required = false) Integer age,@ApiParam(value = "number of badges of the trainer to delete") @Valid @RequestParam(value = "numberOfBadges", required = false) Integer numberOfBadges) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getTrainerById", notes = "get a trainer by its ID", response = TrainerWithId.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TrainerWithId.class),
            @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/trainers/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<TrainerWithId> getTrainerById(@ApiParam(value = "The trainer ID",required=true) @PathVariable("id") Integer id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "getTrainers", notes = "get the list of all trainers", response = TrainerWithId.class, responseContainer = "List", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = TrainerWithId.class, responseContainer = "List") })
    @RequestMapping(value = "/trainers",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<List<TrainerWithId>> getTrainers(@ApiParam(value = "name of the trainer to return") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "surname of the trainer to return") @Valid @RequestParam(value = "surname", required = false) String surname,@ApiParam(value = "gender of the trainer to return") @Valid @RequestParam(value = "gender", required = false) String gender,@ApiParam(value = "age of the trainer to return") @Valid @RequestParam(value = "age", required = false) Integer age,@ApiParam(value = "number of badges of the trainer to return") @Valid @RequestParam(value = "numberOfBadges", required = false) Integer numberOfBadges) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "", nickname = "updateTrainerById", notes = "update a trainer by its ID", tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/trainers/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Void> updateTrainerById(@ApiParam(value = "The pokemon ID",required=true) @PathVariable("id") Integer id,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Trainer pokemon) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}