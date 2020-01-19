package spring.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import spring.entities.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {
    List<RoleEntity> findByActorEntity_Id(Long actorId, Pageable pageable);
    List<RoleEntity> findByMovieEntity_Id(Long movieId, Pageable pageable);
    Optional<RoleEntity> findByActorEntity_IdAndMovieEntity_Id(Long actorId, Long movieId);
}
