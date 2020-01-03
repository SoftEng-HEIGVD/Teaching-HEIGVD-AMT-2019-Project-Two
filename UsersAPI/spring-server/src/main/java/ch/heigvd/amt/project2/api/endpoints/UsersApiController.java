package ch.heigvd.amt.project2.api.endpoints;

import ch.heigvd.amt.project2.api.UsersApi;
import ch.heigvd.amt.project2.api.model.User;
import ch.heigvd.amt.project2.entities.UserEntity;
import ch.heigvd.amt.project2.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-16T19:36:34.802Z")

@RestController
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUserById(@ApiParam(value = "",required=true) @PathVariable("userId") Long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        User user = toUser(userEntity.get());
        return ResponseEntity.ok(user);
    }

    //@Override
    public ResponseEntity<Void> changePassword(@ApiParam(value = "",required=true) @PathVariable("userId") Long userId, @ApiParam(value = "") @Valid @RequestParam(value = "newPassword", required = false) String newPassword) {
        userRepository.changePassword(newPassword, userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Object> createUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User username) {
        UserEntity newUserEntity = toUserEntity(username);
        userRepository.save(newUserEntity);
        Long id = newUserEntity.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
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
        return user;
    }
}
