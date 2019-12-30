package spring.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.entities.MovieEntity;

public interface MoviesRepository extends CrudRepository<MovieEntity, Long>{

}
