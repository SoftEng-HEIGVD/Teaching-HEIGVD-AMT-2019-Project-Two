package spring.api.services;

import org.springframework.stereotype.Component;
import spring.entities.UserEntity;
import spring.model.User;

@Component
public class DtoConverter {

    public UserEntity toUserEntity(User user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .username(user.getUsername()).build();
    }

    public User toUser(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.firstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        return user;
    }
}
