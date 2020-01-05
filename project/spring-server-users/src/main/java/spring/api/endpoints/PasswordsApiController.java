package spring.api.endpoints;

import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import spring.api.PasswordsApi;
import spring.api.util.jwt.JwtUtil;
import spring.entities.UserEntity;
import spring.repositories.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PasswordsApiController implements PasswordsApi {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Void> changePassword(String authorization, @Valid String newPassword) {
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
    }
}
