package users.api.spec.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import okhttp3.OkHttpClient;
import users.ApiException;
import users.api.UserApi;
import users.api.dto.JwtToken;
import users.api.spec.helpers.Environment;

import static org.junit.Assert.assertNotEquals;

public class AuthenticationSteps {

    private Environment environment;
    private UserApi api;

    OkHttpClient client;

    public AuthenticationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getUserApi();
        this.client = environment.getHttpClient();
    }


    @When("^I POST his credentials to the /authentications endpoint$")
    public void iPOSTHisCredentialsToTheAuthenticationsEndpoint() {
        try {
            this.environment.setLastApiResponse(api.loginUserWithHttpInfo(environment.getTestUser()));
            this.environment.setLastApiCallThrewException(false);
            this.environment.setLastApiException(null);
            this.environment.setLastStatusCode(this.environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            this.environment.setLastApiResponse(null);
            this.environment.setLastApiCallThrewException(true);
            this.environment.setLastApiException(e);
            this.environment.setLastStatusCode(this.environment.getLastApiException().getCode());
        }
    }

    @Then("^I receive a jwt token$")
    public void iReceiveAJwtToken() {
        assertNotEquals(null, this.environment.getLastApiResponse().getData());
        this.environment.setLastJwtToken((JwtToken) this.environment.getLastApiResponse().getData());
    }
}
