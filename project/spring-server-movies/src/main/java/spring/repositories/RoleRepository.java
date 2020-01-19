package spring.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import spring.entities.RoleEntity;

import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {
    List<RoleEntity> findByActorEntity_Id(Long actorId);
    List<RoleEntity> findByMovieEntity_Id(Long movieId);
}
