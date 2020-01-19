package spring.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import spring.entities.ActorEntity;

public interface ActorsRepository extends PagingAndSortingRepository<ActorEntity, Long> {

}
