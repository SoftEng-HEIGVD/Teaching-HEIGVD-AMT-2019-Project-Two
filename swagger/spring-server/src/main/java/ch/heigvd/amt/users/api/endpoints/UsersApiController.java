package ch.heigvd.amt.users.api.endpoints;


import ch.heigvd.amt.users.api.UsersApi;
import ch.heigvd.amt.users.api.model.InlineObject;
import ch.heigvd.amt.users.api.model.User;
import ch.heigvd.amt.users.api.util.HashPassword;
import ch.heigvd.amt.users.entities.UserEntity;
import ch.heigvd.amt.users.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    HashPassword hashPassword;

    public ResponseEntity<Void> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody User user) {
        Boolean role = (Boolean) httpServletRequest.getAttribute("role");

        if(role){
            UserEntity newUserEntity = toUserEntity(user);
            UserEntity verifUser = userRepository.findByEmail(user.getEmail());
            if(verifUser == null){
                userRepository.save(newUserEntity);
                return new ResponseEntity<>(null, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }


    public ResponseEntity<Void> updateUser(@ApiParam(value = "",required=true) @PathVariable("email") String email, @ApiParam(value = "" ,required=true )  @Valid @RequestBody InlineObject password) {
        String token = (String) httpServletRequest.getAttribute("email");

        if(token.equals(email)){
            UserEntity userEntity = userRepository.findByEmail(token);

            userEntity.setPassword(hashPassword.hashPassword(password.getPassword()));
            userRepository.save(userEntity);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
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

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPassword(entity.getPassword());
        return user;
    }

}
