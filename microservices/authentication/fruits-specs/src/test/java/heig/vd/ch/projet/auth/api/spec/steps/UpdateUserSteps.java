package heig.vd.ch.projet.auth.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.auth.ApiException;
import heig.vd.ch.projet.auth.api.DefaultApi;
import heig.vd.ch.projet.auth.api.dto.AuthDTO;
import heig.vd.ch.projet.auth.api.spec.helpers.Environment;

public class UpdateUserSteps {
    private Environment environment;
    private DefaultApi api;

    public UpdateUserSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^I have an admin credential$")
    public void i_have_an_admin_credential() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail("admin@admin.ch");
        authDTO.setPassword("admin");

        environment.setAuthDTO(authDTO);
    }

    @Given("^I have an user credential$")
    public void i_have_an_user_credential() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail("user@user.ch");
        authDTO.setPassword("user");
        environment.setAuthDTO(authDTO);
    }


    @When("^I POST it to the /authentication endpoint$")
    public void i_post_it_to_the_authentication_endpoint() {
        try {
            environment.setLastApiResponse(api.authenticationWithHttpInfo(environment.getAuthDTO()));
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

    @Given("^I have credential with wrong password$")
    public void i_have_credential_with_wrong_password() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail("admin@admin.ch");
        authDTO.setPassword("toto");
        environment.setAuthDTO(authDTO);

    }

    @Given("^I have credential with no store email$")
    public void i_have_credential_with_no_store_email() {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setEmail("toto@toto.ch");
        authDTO.setPassword("toto");
        environment.setAuthDTO(authDTO);
    }
}
