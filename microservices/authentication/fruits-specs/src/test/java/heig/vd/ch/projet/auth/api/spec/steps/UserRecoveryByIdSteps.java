package heig.vd.ch.projet.auth.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.auth.ApiException;
import heig.vd.ch.projet.auth.api.DefaultApi;
import heig.vd.ch.projet.auth.api.dto.UserDTO;
import heig.vd.ch.projet.auth.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;

public class UserRecoveryByIdSteps {
    private Environment environment;
    private DefaultApi api;

    public UserRecoveryByIdSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I GET it to the /users/(\\S+) endpoint$")
    public void i_get_it_to_the_users_endpoint(String email) {
        try {
            environment.setLastApiResponse(api.getUserByIdWithHttpInfo(email,environment.getToken()));
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

    @Then("^I receive a user with email (\\S+)$")
    public void i_receive_a_user_with_email(String email) {
        UserDTO userDTO = (UserDTO)environment.getLastApiResponse().getData();
        assertEquals(email,userDTO.getEmail());
    }
}
