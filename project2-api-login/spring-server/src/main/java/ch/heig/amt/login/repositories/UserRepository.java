package ch.heig.amt.login.repositories;

import ch.heig.amt.login.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByusername(String username);
}
