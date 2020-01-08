package heig.vd.ch.projet.auth.api.endpoints;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import heig.vd.ch.projet.auth.api.AuthenticationApi;
import heig.vd.ch.projet.auth.api.model.AuthDTO;
import heig.vd.ch.projet.auth.api.model.Token;
import heig.vd.ch.projet.auth.api.service.AuthenticateService;
import heig.vd.ch.projet.auth.api.service.JWTService;
import heig.vd.ch.projet.auth.entities.UserEntity;
import heig.vd.ch.projet.auth.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class AuthenticationApiController implements AuthenticationApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    JWTService jwtService;

    @Override
    public ResponseEntity<String> authentication(@ApiParam(value = "", required = true) @Valid @RequestBody AuthDTO authDTO) {

        try {
            //Get the existing user
            UserEntity userEntity = userRepository.findById(authDTO.getEmail()).get();

            //Check password with user db
            boolean check = authenticateService.checkPassord(authDTO.getPassword(),userEntity.getPassword());

            if(check){
                //Create a token
                String token = jwtService.createToken(userEntity);

                //Return a ok status with token in the body
                return ResponseEntity.ok(token);
            }else {
                //Return an unauthorized status (401) if authenticate fail
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch (NoSuchElementException e){
            //Return an not found status (401)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
