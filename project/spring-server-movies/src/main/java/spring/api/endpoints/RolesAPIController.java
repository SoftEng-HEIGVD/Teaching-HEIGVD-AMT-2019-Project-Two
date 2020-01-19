package spring.api.endpoints;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import spring.api.RolesApi;
import spring.api.services.RolesService;
import spring.model.Role;
import spring.model.RoleIdentifier;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-01-19T03:06:07.136947+01:00[Europe/Zurich]")

@Controller
public class RolesAPIController implements RolesApi {

    private static final Logger log = LoggerFactory.getLogger(MoviesAPIController.class);

    @Autowired
    RolesService rolesService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<Object> createRole(@ApiParam(value = "Created role object" ,required=true )  @Valid @RequestBody Role role) throws Exception {
        String requestOwner = (String) httpServletRequest.getAttribute("owner");
        return ResponseEntity.created(rolesService.saveRole(role, requestOwner)).build();
    }

    @Override
    public ResponseEntity<Void> fireActor(@ApiParam(value = "id of the actor and the id of the movie of the role to delete" ,required=true )  @Valid @RequestBody RoleIdentifier roleId) throws Exception {
        String requestOwner = (String) httpServletRequest.getAttribute("owner");
        rolesService.deleteRole(roleId.getActorId(), roleId.getMovieId(), requestOwner);
        return ResponseEntity.ok().build();
    }
}
