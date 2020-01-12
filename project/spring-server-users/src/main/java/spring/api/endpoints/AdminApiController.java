package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.AdminApi;
import spring.api.ApiUtil;
import spring.api.services.DtoConverter;
import spring.entities.UserEntity;
import spring.model.User;
import spring.repositories.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminApiController implements AdminApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DtoConverter dtoConverter;

    @Override
    public ResponseEntity<Object> createUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody User user) {
        UserEntity userEntity = dtoConverter.toUserEntity(user);
        userRepository.save(userEntity);
        String username = userEntity.getUsername();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "username of the user",required=true) @PathVariable("username") String username) {
        userRepository.deleteById(username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<User>> findAllUsers() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"password\" : \"password\", \"isAdmin\" : true, \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(dtoConverter.toUser(userEntity));
        }

        if(users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<Object> getUserByName(@ApiParam(value = "The username of the user to be fetched.",required=true) @PathVariable("username") String username) {
        Optional<UserEntity> userEntity = userRepository.findById(username);
        return userEntity.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
