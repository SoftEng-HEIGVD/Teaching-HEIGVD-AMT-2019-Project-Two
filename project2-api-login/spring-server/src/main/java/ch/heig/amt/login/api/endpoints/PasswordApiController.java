package ch.heig.amt.login.api.endpoints;

import ch.heig.amt.login.api.ApiUtil;
import ch.heig.amt.login.api.LoginApi;
import ch.heig.amt.login.api.PasswordApi;
import ch.heig.amt.login.api.UsersApi;
import ch.heig.amt.login.api.exceptions.BadLoginException;
import ch.heig.amt.login.api.model.Credentials;
import ch.heig.amt.login.api.model.QueryPasswordChange;
import ch.heig.amt.login.api.model.UserToGet;
import ch.heig.amt.login.api.model.ValidCreds;
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

import javax.validation.Valid;

@RestController
public class PasswordApiController implements PasswordApi {
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<UserToGet> changePassword(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization, @ApiParam(value = "" ,required=true )  @Valid @RequestBody QueryPasswordChange queryPassword) {
        Claims claims = UtilsJWT.decodeJWT(authorization);
        String username = claims.getSubject();
        UserEntity fetchedUser = userRepository.findByusername(username);

        PasswordHash ph = new PasswordHash();
        boolean validPass;
        String newPassHashed;
        try{
            validPass = ph.validatePassword(queryPassword.getCurrentPassword(), fetchedUser.getPassword());
            newPassHashed = ph.createHash(queryPassword.getNewPassword());
        } catch (Exception e){
            throw new RuntimeException();
        }

        if(validPass){
            fetchedUser.setPassword(newPassHashed);
            userRepository.save(fetchedUser);
            UserToGet userToGet = UsersApiController.toUserToGet(fetchedUser);
            return ResponseEntity.ok(userToGet);
        }
        else{
            throw new BadLoginException("Bad login");
        }
    }
}
