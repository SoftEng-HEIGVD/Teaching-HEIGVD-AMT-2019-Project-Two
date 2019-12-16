package ch.heigvd.amt.project2.repositories;

import ch.heigvd.amt.project2.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
