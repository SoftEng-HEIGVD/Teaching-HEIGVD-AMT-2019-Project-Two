package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.SignUpApi;
import ch.heigvd.amt.project2.api.model.User;
import ch.heigvd.amt.project2.entities.UserEntity;
import ch.heigvd.amt.project2.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class SignUpApiController implements SignUpApi {

    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignUpApiController() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<Void> createUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserEntity newUserEntity = toUserEntity(user);
        userRepository.save(newUserEntity);
        Long id = newUserEntity.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(newUserEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setFirstname(entity.getFirstname());
        user.setLastname(entity.getLastname());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setEmail(entity.getEmail());
        user.setRole(entity.getRole());
        return user;
    }
}
