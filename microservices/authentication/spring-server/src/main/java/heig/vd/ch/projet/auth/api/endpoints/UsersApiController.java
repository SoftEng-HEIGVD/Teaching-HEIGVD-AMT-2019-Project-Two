package heig.vd.ch.projet.auth.api.endpoints;


import com.auth0.jwt.exceptions.JWTVerificationException;
import heig.vd.ch.projet.auth.api.model.Password;
import heig.vd.ch.projet.auth.api.model.Roles;
import heig.vd.ch.projet.auth.api.model.UserDTO;
import heig.vd.ch.projet.auth.api.service.AuthenticateService;
import heig.vd.ch.projet.auth.api.service.DecodedToken;
import heig.vd.ch.projet.auth.api.service.JWTService;
import heig.vd.ch.projet.auth.entities.UserEntity;
import heig.vd.ch.projet.auth.api.UsersApi;
import heig.vd.ch.projet.auth.api.model.User;
import heig.vd.ch.projet.auth.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    JWTService jwtService;

    @Override
    public ResponseEntity<Void> createUser(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,
                                           @ApiParam(value = "", required = true) @Valid @RequestBody User user) {
        //Verify token TODO --> change and make function
        if(!authorization.equals("myToken")){
            //Return an forbidden status (403)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        //Check if email is used
        if(userRepository.existsById(user.getEmail())){
            //Return a conflict status
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        //create a user
        UserEntity newUserEntity = toUserEntity(user);

        //Save the user
        userRepository.save(newUserEntity);

        //TODO send email with email + password un body

        //Return a created status (201)
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Override
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "",required=true ) @PathVariable("userEmail") String userEmail,
                                           @ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization) {

        //Verify token TODO --> change and make function
        /*if(!authorization.equals("myToken")){
            //Return an forbidden status (403)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }*/

        try {
            DecodedToken decodedToken = jwtService.verifyToken(authorization);
            if(decodedToken.getRole().equals(Roles.ADMIN.toString())){
                //Get the existing user
                UserEntity userEntity = userRepository.findById(userEmail).get();

                //Delete the user
                userRepository.delete(userEntity);

                //Return an accept status (202)
                return ResponseEntity.accepted().build();
            }else {
                //Return an forbidden (403)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }catch (NoSuchElementException e){
            //Return an not found status status (404)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (JWTVerificationException | NullPointerException ex){
            //Return an unauthorized status (401)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @Override
    public ResponseEntity<UserDTO> getUserById(@ApiParam(value = "",required=true ) @PathVariable("userEmail") String userEmail,
                                               @ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization) {
        //Verify token TODO --> change and make function
        /*if(!authorization.equals("myToken")){
            //Return an forbidden status (403)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }*/

        try {

            DecodedToken decodedToken = jwtService.verifyToken(authorization);
            if((decodedToken.getEmail().equals(userEmail) && decodedToken.getRole().equals(Roles.USER.toString())) || decodedToken.getRole().equals(Roles.ADMIN.toString())) {
                //Get the existing user
                UserEntity userEntity = userRepository.findById(userEmail).get();

                //Change userEntity to userDTO
                UserDTO userDTO = toUserDTO(userEntity);

                //Return the user and ok status (200)
                return ResponseEntity.ok(userDTO);
            }else {
                //Return an forbidden status (403)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

        }catch(NoSuchElementException e) {
            //Return an not found status (404)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (JWTVerificationException | NullPointerException ex){
            //Return an unauthorized status (401)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization) {

        List<UserDTO> usersDTO = new ArrayList<>();

        //Verify token TODO --> change and make function
        /*if(!authorization.equals("myToken")){
            //Return an forbidden status (403)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }*/

        try {
            DecodedToken decodedToken = jwtService.verifyToken(authorization);
            if(decodedToken.getRole().equals(Roles.ADMIN.toString())){
                //Get all users
                for (UserEntity userEntity : userRepository.findAll()) {
                    usersDTO.add(toUserDTO(userEntity));
                }

                //Return an array of user and an ok status (200)
                return ResponseEntity.ok(usersDTO);
            }else {
                //Return an forbidden status (403)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }catch (JWTVerificationException | NullPointerException ex){
            //Return an unauthorized status (401)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<Void> updateUser(@ApiParam(value = "",required=true ) @PathVariable("userEmail") String userEmail,
                                           @ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,
                                           @ApiParam(value = "", required = true) @Valid @RequestBody Password password) {

        //Verify token TODO --> change and make function
        /*if(!authorization.equals("myToken")){
            //Return an forbidden status (403)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }*/

        try {
            DecodedToken decodedToken = jwtService.verifyToken(authorization);
            if((decodedToken.getEmail().equals(userEmail) && decodedToken.getRole().equals(Roles.USER.toString()))
                    || decodedToken.getRole().equals(Roles.ADMIN.toString())){

                //Get the existing user
                UserEntity userEntity = userRepository.findById(userEmail).get();

                //Update the user password
                String hashedPassword = authenticateService.hashPassword(password.getPassword());
                userEntity.setPassword(hashedPassword);

                //Save the user
                userRepository.save(userEntity);

                //Return an accept status (202)
                return ResponseEntity.accepted().build();

            }else{
                //Return an forbidden status (403)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }catch (NoSuchElementException e){
            //Return an not found status (404)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (JWTVerificationException | NullPointerException ex){
            //Return an unauthorized status (401)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }


    /*Utils methods---------------------------------------------------------------------------------------------------*/
    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setLastname(user.getLastname());
        entity.setFirstname(user.getFirstname());

        String hashedPassword = authenticateService.hashPassword(user.getPassword());
        System.out.println(hashedPassword);
        entity.setPassword(hashedPassword);

        entity.setRole(user.getRole().toString());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setEmail(entity.getEmail());
        user.setLastname(entity.getLastname());
        user.setFirstname(entity.getFirstname());
        user.setPassword(entity.getPassword());
        user.setRole(Roles.fromValue(entity.getRole()));
        return user;
    }

    private UserDTO toUserDTO(UserEntity entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(entity.getEmail());
        userDTO.setLastname(entity.getLastname());
        userDTO.setFirstname(entity.getFirstname());
        userDTO.setRole(Roles.fromValue(entity.getRole()));

        return userDTO;
    }

}
