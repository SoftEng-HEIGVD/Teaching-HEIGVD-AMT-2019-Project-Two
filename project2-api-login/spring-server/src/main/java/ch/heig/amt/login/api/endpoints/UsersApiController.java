package ch.heig.amt.login.api.endpoints;

import ch.heig.amt.login.api.ApiUtil;
import ch.heig.amt.login.api.UsersApi;
import ch.heig.amt.login.api.model.UserToGet;
import ch.heig.amt.login.api.model.UserToPost;
import ch.heig.amt.login.entities.UserEntity;
import ch.heig.amt.login.repositories.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class UsersApiController implements UsersApi {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<UserToGet> createAccount(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserToPost user) {
        UserEntity userEntity = toUserEntity(user);
        UserEntity createdUserEntity = userRepository.save(userEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUserEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(toUserToGet(createdUserEntity));
    }



    public ResponseEntity<UserToGet> getUserbyID(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization, @ApiParam(value = "",required=true) @PathVariable("id") Integer id) {
        return null;
    }

    public UserEntity toUserEntity(UserToPost user){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setMail(user.getMail());
        userEntity.setPassword(user.getPassword());
        userEntity.setUsername(user.getUsername());
        return userEntity;
    }

    public UserToGet toUserToGet(UserEntity userEntity){
        UserToGet userToGet = new UserToGet();
        userToGet.setFirstname(userEntity.getFirstname());
        userToGet.setId(userEntity.getId());
        userToGet.setUsername(userEntity.getUsername());
        userToGet.setLastname(userEntity.getLastname());
        userToGet.setMail(userEntity.getMail());
        return userToGet;
    }
}
