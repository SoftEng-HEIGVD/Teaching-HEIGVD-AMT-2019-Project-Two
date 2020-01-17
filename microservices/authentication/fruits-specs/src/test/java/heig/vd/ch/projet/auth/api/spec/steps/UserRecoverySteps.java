package heig.vd.ch.projet.auth.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.auth.ApiException;
import heig.vd.ch.projet.auth.api.DefaultApi;
import heig.vd.ch.projet.auth.api.dto.AuthDTO;
import heig.vd.ch.projet.auth.api.spec.helpers.Environment;

import static org.junit.Assert.assertNotNull;

public class UserRecoverySteps {
    private Environment environment;
    private DefaultApi api;

    public UserRecoverySteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^I have an admin token$")
    public void i_have_an_admin_token() {
        try {
            environment.setToken(api.authentication(environment.getAuthDTO()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Given("^I have a user token$")
    public void i_have_an_user_token() {
        try {
            environment.setToken(api.authentication(environment.getAuthDTO()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Given("^I have a bad token$")
    public void i_have_a_bad_token() {
        environment.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJlbWFpbCI6ImFkbWluQGFkbWluLmNoIn0.elXPVmY4-sfOn6Qk4mFt85_gOvMjVrm02FDeAFfp3xU");
    }

    @When("^I GET it to the /users endpoint$")
    public void i_get_it_to_the_users_endpoint() {
        try {
            environment.setLastApiResponse(api.getUsersWithHttpInfo(environment.getToken(),0,10));
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
}
