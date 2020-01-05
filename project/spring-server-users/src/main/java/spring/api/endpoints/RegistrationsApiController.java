package spring.api.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.RegistrationsApi;
import spring.api.util.DtoConverter;
import spring.entities.UserEntity;
import spring.model.User;
import spring.repositories.UserRepository;

import javax.validation.Valid;
import java.net.URI;

@Controller
public class RegistrationsApiController implements RegistrationsApi {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Object> registerUser(@Valid User user) {
        UserEntity userEntity = DtoConverter.toUserEntity(user);
        userRepository.save(userEntity);
        String username = userEntity.getUsername();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUri();

        return ResponseEntity.created(location).build();
    }
}
