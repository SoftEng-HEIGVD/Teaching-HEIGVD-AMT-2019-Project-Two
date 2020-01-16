package heig.vd.ch.projet.auth.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.auth.api.dto.AuthDTO;
import heig.vd.ch.projet.auth.api.dto.Roles;
import heig.vd.ch.projet.auth.api.dto.UserDTO;
import heig.vd.ch.projet.auth.api.spec.helpers.Environment;
import heig.vd.ch.projet.auth.ApiException;
import heig.vd.ch.projet.auth.ApiResponse;
import heig.vd.ch.projet.auth.api.DefaultApi;
import heig.vd.ch.projet.auth.api.dto.User;

import java.sql.Timestamp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationSteps {

    private Environment environment;
    private DefaultApi api;

    User user;
    String token;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^there is a Users server$")
    public void there_is_a_Users_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a user payload$")
    public void i_have_a_user_payload() throws Throwable {
        user = new User();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        user.setEmail("francois.burgener" + timestamp.getNanos() + "@heig-vd.ch");
        user.setFirstname("François");
        user.setLastname("Burgener");
        user.setPassword("toto");
        user.setRole(Roles.USER);
    }



    @When("^I POST it to the /users endpoint with admin token$")
    public void i_POST_it_to_the_users_endpoint_with_admin_token() throws Throwable {
        try {
            AuthDTO authDTO = UtilsStep.i_have_an_admin_credential();
            token = api.authentication(authDTO);

            // Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJlbWFpbCI6ImFkbWluQGFkbWluLmNoIn0.elXPVmY4-sfOn6Qk4mFt85_gOvMjVrm02FDeAFfp3xU
            environment.setLastApiResponse(api.createUserWithHttpInfo(token,user));
            environment.setLastApiCallThrewException(false);
            environment.setLastApiException(null);
            environment.setLastStatusCode(environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            environment.setLastApiCallThrewException(true);
            environment.setLastApiResponse(null);
            environment.setLastApiException(e);
            environment.setLastStatusCode(environment.getLastApiException().getCode());
        }
    }

    @When("^I POST it to the /users endpoint with user token$")
    public void i_POST_it_to_the_users_endpoint_with_user_token() throws Throwable {
        try {
            AuthDTO authDTO = UtilsStep.i_have_a_user_credential();
            token = api.authentication(authDTO);

            // Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJlbWFpbCI6ImFkbWluQGFkbWluLmNoIn0.elXPVmY4-sfOn6Qk4mFt85_gOvMjVrm02FDeAFfp3xU
            environment.setLastApiResponse(api.createUserWithHttpInfo(token,user));
            environment.setLastApiCallThrewException(false);
            environment.setLastApiException(null);
            environment.setLastStatusCode(environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            environment.setLastApiCallThrewException(true);
            environment.setLastApiResponse(null);
            environment.setLastApiException(e);
            environment.setLastStatusCode(environment.getLastApiException().getCode());
        }
    }

    @When("^I POST it to the /users endpoint with bad token$")
    public void i_post_it_to_the_users_endpoints_with_bad_token() {
        try {
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJlbWFpbCI6ImFkbWluQGFkbWluLmNoIn0.elXPVmY4-sfOn6Qk4mFt85_gOvMjVrm02FDeAFfp3xU";
            environment.setLastApiResponse(api.createUserWithHttpInfo(token,user));
            environment.setLastApiCallThrewException(false);
            environment.setLastApiException(null);
            environment.setLastStatusCode(environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            environment.setLastApiCallThrewException(true);
            environment.setLastApiResponse(null);
            environment.setLastApiException(e);
            environment.setLastStatusCode(environment.getLastApiException().getCode());
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int code) throws Throwable {
        assertEquals(code, environment.getLastStatusCode());
    }

    @Then("^I added successfully a user$")
    public void i_added_successfully_a_user()  {
        try {
            UserDTO userDTO = api.getUserById(user.getEmail(),token);
            assertEquals(userDTO.getEmail(),user.getEmail());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
