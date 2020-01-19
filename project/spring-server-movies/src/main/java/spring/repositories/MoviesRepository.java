package spring.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import spring.entities.MovieEntity;

public interface MoviesRepository extends PagingAndSortingRepository<MovieEntity, Long> {

}
