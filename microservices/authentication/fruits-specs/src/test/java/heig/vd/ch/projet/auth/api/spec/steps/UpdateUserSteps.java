package heig.vd.ch.projet.auth.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.auth.ApiException;
import heig.vd.ch.projet.auth.api.DefaultApi;
import heig.vd.ch.projet.auth.api.dto.AuthDTO;
import heig.vd.ch.projet.auth.api.dto.Password;
import heig.vd.ch.projet.auth.api.spec.helpers.Environment;

public class UpdateUserSteps {
    private Environment environment;
    private DefaultApi api;

    Password password;

    public UpdateUserSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I POST it to the /users/(\\S+) endpoint$")
    public void i_post_it_to_the_users_email_endpoint(String email) {
        try {
            environment.setLastApiResponse(api.updateUserWithHttpInfo(email,environment.getToken(),password));
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


    @Given("^I have a new password (\\S+)$")
    public void i_have_a_new_password(String psw) {
        password = new Password();
        password.setPassword(psw);
    }
}
