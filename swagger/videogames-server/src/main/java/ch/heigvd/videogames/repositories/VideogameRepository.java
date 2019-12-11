package ch.heigvd.videogames.repositories;

import ch.heigvd.videogames.entities.VideogameEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface VideogameRepository extends CrudRepository<VideogameEntity, Long>{

}
