package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import spring.api.LoginApi;
import spring.api.util.jwt.JwtUtil;
import spring.entities.UserEntity;
import spring.model.JwtToken;
import spring.model.User;
import spring.repositories.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginApiController implements LoginApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public ResponseEntity<JwtToken> loginUser(@ApiParam(value = "credentials" ,required=true )  @Valid @RequestBody User user) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(user.getUsername());
        if(userEntityOpt.isPresent()) {
            // Check password
            UserEntity userEntity = userEntityOpt.get();
            if(user.getPassword().equals(userEntity.getPassword())) {
                String tokenString = jwtUtil.createToken(userEntity.getUsername(), userEntity.isAdmin());
                JwtToken token = new JwtToken();
                token.setToken(tokenString);
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
