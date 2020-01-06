package ch.heigvd.amt.users.api.endpoints;

import ch.heigvd.amt.users.api.LoginApi;
import ch.heigvd.amt.users.api.exceptions.ApiException;
import ch.heigvd.amt.users.api.model.UserLogin;
import ch.heigvd.amt.users.api.util.JWTToken;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class LoginApiController implements LoginApi {

    @Autowired
    JWTToken jwtToken;

    public ResponseEntity<String> loginUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserLogin credentials) {
        String newToken = jwtToken.createJWT(credentials);
        return ResponseEntity.ok().body(newToken);

    }
}
