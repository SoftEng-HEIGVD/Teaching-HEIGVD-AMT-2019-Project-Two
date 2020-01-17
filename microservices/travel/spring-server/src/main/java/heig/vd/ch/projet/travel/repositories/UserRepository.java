package heig.vd.ch.projet.travel.repositories;

import heig.vd.ch.projet.travel.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}

