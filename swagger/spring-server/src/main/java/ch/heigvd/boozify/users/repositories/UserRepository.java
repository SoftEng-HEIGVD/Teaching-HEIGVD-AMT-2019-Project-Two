package ch.heigvd.boozify.users.repositories;

import ch.heigvd.boozify.users.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long>{

}
