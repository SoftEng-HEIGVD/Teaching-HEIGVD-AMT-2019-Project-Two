package ch.heigvd.boozify.users.api.endpoints;

import ch.heigvd.boozify.users.api.ApiUtil;
import ch.heigvd.boozify.users.api.UsersApi;
import ch.heigvd.boozify.users.model.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UsersApiController implements UsersApi {

    public ResponseEntity<Object> createUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    public ResponseEntity<List<User>> getUsers() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"administrateur\" : true, \"password\" : \"password\", \"name\" : \"name\", \"id_user\" : 0, \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
