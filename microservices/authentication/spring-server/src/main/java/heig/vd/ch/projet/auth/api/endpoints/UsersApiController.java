package heig.vd.ch.projet.auth.api.endpoints;


import heig.vd.ch.projet.auth.entities.UserEntity;
import heig.vd.ch.projet.auth.api.UserApi;
import heig.vd.ch.projet.auth.api.model.User;
import heig.vd.ch.projet.auth.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UsersApiController implements UserApi {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> createUser( @ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,
                                              @ApiParam(value = "", required = true) @Valid @RequestBody User user) {
        UserEntity newUserEntity = toUserEntity(user);
        userRepository.save(newUserEntity);
        Long id = newUserEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUserEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<User>> getUsers(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization) {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUserById(@ApiParam(value = "",required=true ) @PathVariable("userId") Long userId,
                                            @ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization) {

        //TODO Here decode token
        System.out.println(userId);
        System.out.println(authorization);

        // Get the user by id
        UserEntity userEntity = userRepository.findById(userId).get();
        User user = toUser(userEntity);
        return ResponseEntity.ok(user);
    }



    /*------------------------------------------------------------------------------------------------------------*/
    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setLastname(user.getLastname());
        entity.setFirstname(user.getFirstname());
        entity.setPassword(user.getPassword());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setEmail(entity.getEmail());
        user.setLastname(entity.getLastname());
        user.setFirstname(entity.getFirstname());
        user.setPassword(entity.getPassword());
        return user;
    }

}
