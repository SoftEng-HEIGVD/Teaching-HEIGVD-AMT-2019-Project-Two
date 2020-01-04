package ch.heig.amt.login.api.endpoints;

import ch.heig.amt.login.api.LoginApi;
import ch.heig.amt.login.api.exceptions.BadLoginException;
import ch.heig.amt.login.api.model.Credentials;
import ch.heig.amt.login.api.model.ValidCreds;
import ch.heig.amt.login.api.util.UtilsJWT;
import ch.heig.amt.login.entities.UserEntity;
import ch.heig.amt.login.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginApiController implements LoginApi {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ValidCreds> login(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Credentials credentials) {
        UserEntity fetchedUserEntity = userRepository.findByusername(credentials.getUsername());
        ValidCreds validCreds = new ValidCreds();
        if(fetchedUserEntity.getPassword().equals(credentials.getPassword())){
            validCreds.setUserID(fetchedUserEntity.getId());
            validCreds.setJwTToken(UtilsJWT.createJWT("Login API For Pokemon API", fetchedUserEntity.getUsername(),UtilsJWT.VALIDITY));
        } else{
            throw new BadLoginException("Bad login");
        }
        return ResponseEntity.ok(validCreds);
    }
}
