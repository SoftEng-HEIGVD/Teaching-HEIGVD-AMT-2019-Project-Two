package heig.vd.ch.projet.auth.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.auth.ApiException;
import heig.vd.ch.projet.auth.api.DefaultApi;
import heig.vd.ch.projet.auth.api.dto.AuthDTO;
import heig.vd.ch.projet.auth.api.spec.helpers.Environment;

public class AuthenticationSteps {
    private Environment environment;
    private DefaultApi api;

    AuthDTO authDTO;

    public AuthenticationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^I have an admin credential$")
    public void i_have_an_admin_credential() {
        authDTO = new AuthDTO();
        authDTO.setEmail("admin@admin.ch");
        authDTO.setPassword("admin");
    }


    @When("^I POST it to the /authentication endpoint$")
    public void i_post_it_to_the_authentication_endpoint() {
        try {
            environment.setLastApiResponse(api.authenticationWithHttpInfo(authDTO));
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
