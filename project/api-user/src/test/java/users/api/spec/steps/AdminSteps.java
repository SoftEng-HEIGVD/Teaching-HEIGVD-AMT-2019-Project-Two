package users.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import okhttp3.OkHttpClient;
import users.ApiException;
import users.ApiResponse;
import users.api.AdminApi;
import users.api.UserApi;
import users.api.dto.JwtToken;
import users.api.dto.User;
import users.api.spec.helpers.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdminSteps {

    private Environment environment;
    private UserApi userApi;
    private AdminApi adminApi;


    OkHttpClient client;

    public AdminSteps(Environment environment) {
        this.environment = environment;
        this.userApi = environment.getUserApi();
        this.adminApi = environment.getAdminApi();
        this.client = environment.getHttpClient();
    }

    @Given("^there is an admin$")
    public void thereIsAnAdmin() {
        assertNotNull(this.environment.getAdmin());
    }

    @Given("^there is an admin token$")
    public void thereIsAnAdminToken() throws ApiException {
        ApiResponse adminAuthenticationResponse = userApi.loginUserWithHttpInfo(environment.getAdmin());
        this.environment.setAdminToken((JwtToken) adminAuthenticationResponse.getData());
        this.adminApi.getApiClient().setApiKey("Bearer " + this.environment.getAdminToken().getToken());
    }

    @When("^I POST admin credentials to the /authentications endpoint$")
    public void iPOSTAdminCredentialsToTheAuthenticationsEndpoint() {
        try {
            this.environment.setLastApiResponse(userApi.loginUserWithHttpInfo(environment.getAdmin()));
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

    @Then("^I receive an admin token$")
    public void iReceiveAnAdminToken() {
        assertNotEquals(null, this.environment.getLastApiResponse().getData());
        this.environment.setAdminToken((JwtToken) this.environment.getLastApiResponse().getData());
    }

    @When("^I GET all users$")
    public void iGETAllUsers() {
        try {
            this.environment.setLastApiResponse(adminApi.findAllUsersWithHttpInfo());
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

    @Then("^I received a list of users$")
    public void iReceivedAListOfUsers() {
        assertNotNull(this.environment.getLastApiResponse().getData());
        List<User> users = new ArrayList<>((List<User>) this.environment.getLastApiResponse().getData());
        assertFalse(users.isEmpty());
        assertEquals(users.get(0).getUsername(), "admin");
    }
}
