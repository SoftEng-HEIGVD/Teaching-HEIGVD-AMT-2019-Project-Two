package ch.heig.amt.pokemon.api.endpoints;

import ch.heig.amt.pokemon.api.ApiUtil;
import ch.heig.amt.pokemon.api.TrainersApi;
import ch.heig.amt.pokemon.api.model.Pokemon;
import ch.heig.amt.pokemon.api.model.Trainer;
import ch.heig.amt.pokemon.api.model.TrainerWithId;
import ch.heig.amt.pokemon.entities.PokemonEntity;
import ch.heig.amt.pokemon.entities.TrainerEntity;
import ch.heig.amt.pokemon.repositories.PokemonRepository;
import ch.heig.amt.pokemon.repositories.TrainerRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TrainersApiControllers implements TrainersApi {

    @Autowired
    private TrainerRepository trainerRepository;

    public ResponseEntity<TrainerWithId> createTrainer(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Trainer trainer) {
        TrainerEntity trainerEntity = toTrainerEntity(trainer);
        TrainerEntity createdTrainerEntity = trainerRepository.save(trainerEntity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/trainers/{id}").buildAndExpand(createdTrainerEntity.getTrainerId()).toUri();


        return ResponseEntity.created(uri).body(toTrainerWithId(createdTrainerEntity));
    }


    public ResponseEntity<Void> deleteTrainerById(@ApiParam(value = "The trainer ID",required=true) @PathVariable("id") Integer id) {
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<Void> deleteTrainers(@ApiParam(value = "name of the trainer to delete") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "surname of the trainer to delete") @Valid @RequestParam(value = "surname", required = false) String surname,@ApiParam(value = "gender of the trainer to delete") @Valid @RequestParam(value = "gender", required = false) String gender,@ApiParam(value = "age of the trainer to delete") @Valid @RequestParam(value = "age", required = false) Integer age,@ApiParam(value = "number of badges of the trainer to delete") @Valid @RequestParam(value = "numberOfBadges", required = false) Integer numberOfBadges) {
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<TrainerWithId> getTrainerById(@ApiParam(value = "The trainer ID",required=true) @PathVariable("id") Integer id) {
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<List<TrainerWithId>> getTrainers(@ApiParam(value = "name of the trainer to return") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "surname of the trainer to return") @Valid @RequestParam(value = "surname", required = false) String surname,@ApiParam(value = "gender of the trainer to return") @Valid @RequestParam(value = "gender", required = false) String gender,@ApiParam(value = "age of the trainer to return") @Valid @RequestParam(value = "age", required = false) Integer age,@ApiParam(value = "number of badges of the trainer to return") @Valid @RequestParam(value = "numberOfBadges", required = false) Integer numberOfBadges) {
        List<TrainerWithId> trainers = new ArrayList<>();
        List<TrainerEntity> trainerEntities = new ArrayList<>();

        trainerEntities = (List<TrainerEntity>) trainerRepository.findAll();

            for(TrainerEntity trainerEntity : trainerEntities) {
                trainers.add(toTrainerWithId(trainerEntity));
            }

            return ResponseEntity.ok(trainers);
    }

    public ResponseEntity<Void> updateTrainerById(@ApiParam(value = "The pokemon ID",required=true) @PathVariable("id") Integer id,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Trainer pokemon) {
        return ResponseEntity.notFound().build();
    }

    /* Entity to POJO conversion */
    private TrainerWithId toTrainerWithId(TrainerEntity trainerEntity) {
        TrainerWithId trainerWithId = new TrainerWithId();

        trainerWithId.setId(trainerEntity.getTrainerId());
        trainerWithId.setAge(trainerEntity.getAge());
        trainerWithId.setGender(trainerEntity.getGender());
        trainerWithId.setName(trainerEntity.getName());
        trainerWithId.setNumberOfBadges(trainerEntity.getNumberOfBadges());
        trainerWithId.setSurname(trainerEntity.getSurname());

        return trainerWithId;
    }

    private TrainerEntity toTrainerEntity(Trainer trainer) {
        TrainerEntity trainerEntity = new TrainerEntity();

        trainerEntity.setAge(trainer.getAge());
        trainerEntity.setGender(trainer.getGender());
        trainerEntity.setName(trainer.getName());
        trainerEntity.setNumberOfBadges(trainer.getNumberOfBadges());
        trainerEntity.setSurname(trainer.getSurname());

        return trainerEntity;
    }

}