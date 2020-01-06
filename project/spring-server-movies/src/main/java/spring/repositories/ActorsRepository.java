package spring.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.entities.ActorEntity;

public interface ActorsRepository extends CrudRepository<ActorEntity, Long>{

}
