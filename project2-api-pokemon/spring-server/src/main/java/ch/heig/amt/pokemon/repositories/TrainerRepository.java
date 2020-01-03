package ch.heig.amt.pokemon.repositories;

import ch.heig.amt.pokemon.entities.TrainerEntity;
import org.springframework.data.repository.CrudRepository;

public interface TrainerRepository extends CrudRepository<TrainerEntity, Integer> {
}
