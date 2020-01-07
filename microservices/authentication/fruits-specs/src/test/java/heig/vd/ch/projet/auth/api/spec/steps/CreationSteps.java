package heig.vd.ch.projet.auth.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.auth.api.dto.Roles;
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
        user.setRole(Roles.ADMIN);
    }

    @When("^I POST it to the /users endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        try {
            environment.lastApiResponse = api.createUserWithHttpInfo("myToken",user);
            environment.lastApiCallThrewException = false;
            environment.lastApiException = null;
            environment.lastStatusCode = environment.lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            environment.lastApiCallThrewException = true;
            environment.lastApiResponse = null;
            environment.lastApiException = e;
            environment.lastStatusCode = environment.lastApiException.getCode();
        }

    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int code) throws Throwable {
        assertEquals(code, environment.lastStatusCode);
    }

}
