package ch.heigvd.videogames.repositories;

import ch.heigvd.videogames.entities.VideogameEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Repository
public interface VideogameRepository extends CrudRepository<VideogameEntity, Long>{

}
