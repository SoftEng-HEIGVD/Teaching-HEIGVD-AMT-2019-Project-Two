package spring.api.endpoints;

import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.ApiUtil;
import spring.api.UserApi;
import spring.api.services.DtoConverter;
import spring.api.services.JwtUtil;
import spring.api.services.UserService;
import spring.entities.UserEntity;
import spring.model.JwtToken;
import spring.model.User;
import spring.repositories.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@Controller
public class UserApiController implements UserApi {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<Void> changePassword(@ApiParam(value = "User's new password." ,required=true )  @Valid @RequestBody String newPassword) throws Exception {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<JwtToken> loginUser(@ApiParam(value = "credentials" ,required=true )  @Valid @RequestBody User user) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"token\" : \"token\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return ResponseEntity.ok(userService.authenticateUser(user));
    }

    @Override
    public ResponseEntity<Object> registerUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody User user) throws Exception {
        return ResponseEntity.created(userService.saveUser(user)).build();
    }

    /*@Override
    public ResponseEntity<Void> changePassword(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization, @ApiParam(value = "User's new password." ,required=true )  @Valid @RequestBody String newPassword) {
        // Extract username
        String token = jwtUtil.extractToken(authorization);
        DecodedJWT jwt = jwtUtil.decodeToken(token);

        String username = jwt.getSubject();
        Optional<UserEntity> userEntityOpt = userRepository.findById(username);
        if(userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            userEntity.setPassword(newPassword);
            userRepository.save(userEntity);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}
