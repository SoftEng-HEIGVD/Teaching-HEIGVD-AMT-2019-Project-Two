package spring.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import spring.entities.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {
}
