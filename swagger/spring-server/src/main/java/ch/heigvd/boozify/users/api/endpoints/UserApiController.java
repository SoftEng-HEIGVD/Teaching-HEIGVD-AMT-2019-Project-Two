package ch.heigvd.boozify.users.api.endpoints;

import ch.heigvd.boozify.users.api.UsersApi;
import ch.heigvd.boozify.users.entities.UserEntity;
import ch.heigvd.boozify.users.model.User;
import ch.heigvd.boozify.users.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UserApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    public ResponseEntity<Object> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody User user) {
        Boolean role = (Boolean) httpServletRequest.getAttribute("role");

        if(role.equals("1")){
            UserEntity newUserEntity = toUserEntity(user);
            UserEntity userToDb = userRepository.findByMail(user.getEmail());
            if(userToDb == null){
                userRepository.save(newUserEntity);
                return new ResponseEntity<>(null, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }


    public ResponseEntity<Void> updateUser(@ApiParam(value = "",required=true) @PathVariable("email") String email, @ApiParam(value = "" ,required=true )  @Valid @RequestBody String password) {
        Boolean role = (Boolean) httpServletRequest.getAttribute("role");
        String tokenEmail = (String) httpServletRequest.getAttribute("email");

        if(tokenEmail.equals(email)){
            UserEntity userEntity = userRepository.findByMail(tokenEmail);
            if(userEntity == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            //Todo hashPassword fonction
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }


    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setRole(entity.getRole());
        return user;
    }

}