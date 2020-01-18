package users.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import users.ApiException;
import users.ApiResponse;
import users.api.UserApi;
import users.api.dto.JwtToken;
import users.api.dto.User;
import users.api.spec.helpers.Environment;

import static org.junit.Assert.*;

public class CreationSteps {

    private static Logger log = LoggerFactory.getLogger(CreationSteps.class);

    private Environment environment;
    private UserApi api;

    OkHttpClient client;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getUserApi();
        this.client = environment.getHttpClient();
    }

    @Given("^there is a Users server$")
    public void there_is_a_Users_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a user payload with no username or password$")
    public void iHaveAUserPayloadWithNoUsernameOrPassword() {
       this.environment.setTestUser(new users.api.dto.User());
    }

    @Given("^I have a user payload with username and password$")
    public void iHaveAUserPayloadWithUsernameAndPassword() {
        User user = new users.api.dto.User();
        String timestamp = String.valueOf(System.currentTimeMillis());
        user.setUsername("testUser" + timestamp);
        user.setPassword("testPassword");
        this.environment.setTestUser(user);
    }

    @When("^I POST it to the /registrations endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        try {
            this.environment.setLastApiResponse(api.registerUserWithHttpInfo(environment.getTestUser()));
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

    @When("^I PATCH it to the /passwords endpoint$")
    public void iPATCHItToThePasswordsEndpoint() {
        try {
            this.environment.setLastApiResponse(api.changePasswordWithHttpInfo(environment.getTestUser().getPassword()));
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

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int expectedCode) throws Throwable {
        assertEquals(expectedCode, this.environment.getLastStatusCode());
    }
}
