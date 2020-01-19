package ch.heigvd.amt.users.api.endpoints;

import ch.heigvd.amt.users.api.LoginApi;
import ch.heigvd.amt.users.api.exceptions.ApiException;
import ch.heigvd.amt.users.api.model.User;
import ch.heigvd.amt.users.api.model.UserLogin;
import ch.heigvd.amt.users.api.util.HashPassword;
import ch.heigvd.amt.users.api.util.JWTToken;
import ch.heigvd.amt.users.entities.UserEntity;
import ch.heigvd.amt.users.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class LoginApiController implements LoginApi {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTToken jwtToken;

    public ResponseEntity<String> loginUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserLogin credentials) {

            try{
                String token = jwtToken.createJWT(credentials);
                return ResponseEntity.ok().body(token);
            } catch (ApiException e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
            }


    }
}
