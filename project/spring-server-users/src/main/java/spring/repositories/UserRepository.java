package spring.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
