package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import spring.api.ApiUtil;
import spring.api.UserApi;
import spring.api.services.UserService;
import spring.model.JwtToken;
import spring.model.ProfileUpdate;
import spring.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserApiController implements UserApi {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;

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

    /**
     * Change user's password. The username is extracted from the http request. No need to ask
     * for old password, if jwt token verification passed then we assume the user is already authenticated.
     * @param newPassword the new password of the user
     * @return response entity ok
     * @throws Exception
     */
    @Override
    public ResponseEntity<Void> changePassword(@ApiParam(value = "User's new password." ,required=true )  @Valid @RequestBody String newPassword) throws Exception {
        String username = (String) httpServletRequest.getAttribute("owner");
        userService.changePassword(username, newPassword);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateProfile(@ApiParam(value = "updates to the user profile" ,required=true )  @Valid @RequestBody ProfileUpdate profileUpdate) throws Exception {
        String username = (String) httpServletRequest.getAttribute("owner");
        userService.updateProfile(username, profileUpdate);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> getUserProfile() throws Exception {
        String username = (String) httpServletRequest.getAttribute("owner");
        return ResponseEntity.ok(userService.getUserById(username));
    }
}
