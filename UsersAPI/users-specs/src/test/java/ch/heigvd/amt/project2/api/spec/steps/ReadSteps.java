package ch.heigvd.amt.project2.api.spec.steps;

import ch.heigvd.amt.project2.ApiException;
import ch.heigvd.amt.project2.ApiResponse;
import ch.heigvd.amt.project2.api.UserApi;
import ch.heigvd.amt.project2.api.model.User;
import ch.heigvd.amt.project2.api.spec.helpers.Environment;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReadSteps {

    private Environment environment;
    private UserApi api;

    User user;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    static final long USER_ID = 0;

    public ReadSteps(Environment environment){
        this.environment = environment;
        this.api = environment.getApi();
        this.user = new User();
        this.user.setEmail("test@mail.com");
        this.user.setFirstname("Jane");
        this.user.setLastname("Doe");
        this.user.setPassword("test");
    }

    @Given("^there a User API server$")
    public void there_a_User_API_server() throws Throwable {
        assertNotNull(api);
    }

    @When("^I GET the users from the /users endpoint$")
    public void i_GET_the_users_from_the_users_endpoint() throws Throwable {
        try{
            lastApiResponse = api.getUsersWithHttpInfo();
            lastApiException = null;
            lastApiCallThrewException = false;
            lastStatusCode = lastApiResponse.getStatusCode();
        }catch (ApiException e){
            lastApiResponse = null;
            lastApiException = e;
            lastApiCallThrewException = true;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(200, lastStatusCode);
    }

    @When("^I GET a user from the /users endpoint$")
    public void i_GET_a_user_from_the_users_endpoint() throws Throwable {
        try{
            lastApiResponse = api.getUserByIdWithHttpInfo(USER_ID);
            lastApiException = null;
            lastApiCallThrewException = false;
            lastStatusCode = lastApiResponse.getStatusCode();
        }catch (ApiException e){
            lastApiResponse = null;
            lastApiException = e;
            lastApiCallThrewException = true;
            lastStatusCode = lastApiException.getCode();
        }
    }
}
