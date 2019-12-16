package ch.heigvd.amt.project2.repositories;

import ch.heigvd.amt.project2.entities.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Override
    Optional<UserEntity> findById(Long aLong);

    @Transactional
    @Modifying(clearAutomatically = true) // (flushAutomatically = true)
    @Query("update UserEntity ue set ue.password = ?1 where ue.id = ?2")
    void changePassword(String password, Long userId);
}
