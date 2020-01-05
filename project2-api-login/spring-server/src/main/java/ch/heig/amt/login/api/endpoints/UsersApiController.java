package ch.heig.amt.login.api.endpoints;

import ch.heig.amt.login.api.ApiUtil;
import ch.heig.amt.login.api.UsersApi;
import ch.heig.amt.login.api.exceptions.ForbiddenException;
import ch.heig.amt.login.api.model.UserToGet;
import ch.heig.amt.login.api.model.UserToPost;
import ch.heig.amt.login.api.util.PasswordHash;
import ch.heig.amt.login.api.util.UtilsJWT;
import ch.heig.amt.login.entities.UserEntity;
import ch.heig.amt.login.repositories.UserRepository;
import io.jsonwebtoken.Claims;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.security.AccessControlException;
import java.util.Optional;

@RestController
public class UsersApiController implements UsersApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    public ResponseEntity<UserToGet> createAccount(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserToPost user) {

        UserEntity userEntity = toUserEntity(user);

        // Password is hashed to be entered in database
        PasswordHash ph = new PasswordHash();
        String finalHashedPass = "";
        try {
            finalHashedPass = ph.createHash(userEntity.getPassword());
        } catch (Exception e){
            throw new RuntimeException();
        }
        userEntity.setPassword(finalHashedPass);
        UserEntity createdUserEntity = userRepository.save(userEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUserEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(toUserToGet(createdUserEntity));
    }



    public ResponseEntity<UserToGet> getUser(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization) {
        UserEntity fetchedUser = userRepository.findByusername((String) request.getAttribute("username"));
        UserToGet userToGet = toUserToGet(fetchedUser);

        return ResponseEntity.ok(userToGet);
    }

    public UserEntity toUserEntity(UserToPost user){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setMail(user.getMail());
        userEntity.setPassword(user.getPassword());
        userEntity.setUsername(user.getUsername());
        userEntity.setIsadmin(user.getIsadmin());
        return userEntity;
    }

    public static UserToGet toUserToGet(UserEntity userEntity){
        UserToGet userToGet = new UserToGet();
        userToGet.setFirstname(userEntity.getFirstname());
        userToGet.setId(userEntity.getId());
        userToGet.setUsername(userEntity.getUsername());
        userToGet.setLastname(userEntity.getLastname());
        userToGet.setMail(userEntity.getMail());
        userToGet.setIsadmin(userEntity.getIsadmin());
        return userToGet;
    }
}
