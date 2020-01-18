package ch.heigvd.user.api.endpoints;

import ch.heigvd.user.api.model.InlineObject;
import ch.heigvd.user.api.model.InlineObject1;
import ch.heigvd.user.api.model.InlineObject2;
import ch.heigvd.user.api.model.User;
import ch.heigvd.user.entities.UserEntity;
import ch.heigvd.user.repositories.UserRepository;
import ch.heigvd.user.utils.TokenJwt;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class UserAuthController{

    @Autowired
    private UserRepository userR;


    @PostMapping
    public ResponseEntity<String> userLoginToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody InlineObject2 user){
        Optional<UserEntity> userEntity = userR.findById(user.getEmail());

        if(userEntity.isPresent() && userEntity.get().getPassword().equals(user.getPassword())){
            return ResponseEntity.status(200).body(new TokenJwt().createJWT(userEntity.get()));
        }

        return ResponseEntity.status(400).build();
    }


}
