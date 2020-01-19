package ch.heigvd.amt.users.api.endpoints;

import ch.heigvd.amt.users.api.model.InlineObject;
import ch.heigvd.amt.users.api.util.HashPassword;
import ch.heigvd.amt.users.entities.UserEntity;
import ch.heigvd.amt.users.repositories.UserRepository;
import ch.heigvd.amt.users.api.UsersApi;
import ch.heigvd.amt.users.api.model.User;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest request;

    public ResponseEntity<Void> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody User user) {
        UserEntity newUserEntity = toUserEntity(user);

        HashPassword hashPassword = new HashPassword();
        String passwordHashed;
        try{
            passwordHashed = hashPassword.hashPassword(newUserEntity.getPassword());
        } catch (Exception e){
            throw new RuntimeException();
        }
        newUserEntity.setPassword(passwordHashed);
        userRepository.save(newUserEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(newUserEntity.getEmail()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "",required=true) @PathVariable("email") String email, @ApiParam(value = "" ,required=true )  @Valid @RequestBody InlineObject password) {
        UserEntity updateUserEntity = userRepository.findByEmail(email);

        String updatePassword;
        HashPassword hashPassword = new HashPassword();

        updatePassword = hashPassword.hashPassword(password.getPassword());

        updateUserEntity.setPassword(updatePassword);

        userRepository.save(updateUserEntity);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }



    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setPassword(user.getPassword());
        entity.setAdministrator(user.getAdministrator());
        return entity;
    }


}
