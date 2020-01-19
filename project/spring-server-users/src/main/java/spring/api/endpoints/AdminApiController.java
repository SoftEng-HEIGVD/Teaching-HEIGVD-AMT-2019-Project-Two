package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import spring.api.AdminApi;
import spring.api.ApiUtil;
import spring.api.services.UserService;
import spring.model.User;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

/**
 * Controller for the endpoint reserved for the system admin. He has all the power.
 */
@Controller
public class AdminApiController implements AdminApi {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<Object> createUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody User user) throws Exception  {
        URI location = userService.saveUser(user);

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "username of the user",required=true) @PathVariable("username") String username) throws Exception {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<User>> findAllUsers(@Min(0)@ApiParam(value = "The number of the displayed page.", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page,@Min(0) @Max(100) @ApiParam(value = "The number of items to be displayed on one page.", defaultValue = "20") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"password\" : \"password\", \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        List<User> users = userService.getAllUsers(page, pageSize);

        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<Object> getUserByName(@ApiParam(value = "The username of the user to be fetched.",required=true) @PathVariable("username") String username) throws Exception {
        //Optional<UserEntity> userEntity = userRepository.findById(username);
        //return userEntity.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.ok(userService.getUserById(username));
    }

    @Override
    public ResponseEntity<Void> updateUserBlockedStatus(@ApiParam(value = "The username of the user to be fetched.",required=true) @PathVariable("username") String username,@NotNull @ApiParam(value = "boolean describing whether user should be blocked or not.", required = true) @Valid @RequestParam(value = "blocked", required = true) Boolean blocked) throws Exception {
        userService.updateUserBlockedStatus(username, blocked);
        return ResponseEntity.ok().build();
    }
}
