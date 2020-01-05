package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.UsersApi;
import spring.api.util.DtoConverter;
import spring.api.util.jwt.JwtUtil;
import spring.entities.UserEntity;
import spring.model.User;
import spring.repositories.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public ResponseEntity<Object> createUser(@ApiParam(required = true) @Valid @RequestBody User user) {
        UserEntity userEntity = DtoConverter.toUserEntity(user);
        userRepository.save(userEntity);
        String username = userEntity.getUsername();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> fruits = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            fruits.add(DtoConverter.toUser(userEntity));
        }

        if(fruits.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(fruits);
    }

    @Override
    public ResponseEntity<Object> getUserByName(String username) {
        Optional<UserEntity> userEntity = userRepository.findById(username);
        return userEntity.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteUser(String username) {
        userRepository.deleteById(username);
        return ResponseEntity.ok().build();
    }
}
