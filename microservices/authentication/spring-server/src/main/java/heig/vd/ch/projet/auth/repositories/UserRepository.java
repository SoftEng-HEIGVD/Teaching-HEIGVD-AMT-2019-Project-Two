package heig.vd.ch.projet.auth.repositories;

import heig.vd.ch.projet.auth.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by François Burgener
 */
public interface UserRepository extends CrudRepository<UserEntity, String>{

}
