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

/**
 * Based on the CreationSteps class of Fruits, written by Olivier Liechti
 * @author Nathanaël Mizutani
 */
public class WriteSteps {

    private Environment environment;
    private UserApi api;

    User user;

    private final String NEW_PASSWORD = "test2";

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public WriteSteps(Environment environment){
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is a User Api server$")
    public void there_is_a_User_API_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a user payload$")
    public void i_have_a_user_payload() throws Throwable {
        user = new ch.heigvd.amt.project2.api.model.User();
        user.setEmail("user@mail.com");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setPassword("test");
    }

    @When("^I POST it to the /users endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws  Throwable {
        try{
            lastApiResponse = api.createUserWithHttpInfo(user);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiResponse = null;
            lastApiCallThrewException = true;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code for the creation$")
    public void i_receive_a_status_code_for_the_creation(int arg1) throws Throwable {
        assertEquals(201, lastStatusCode);
    }

    @Given("^I have a new password and a user id$")
    public void i_have_a_new_password_and_a_user_id() throws Throwable {
        assertNotNull(NEW_PASSWORD);
        assertNotNull(user);
    }

    @When("^I PATCH the /users/userId endpoint$")
    public void i_PATCH_the_users_userId_endpoint() throws Throwable {
        try{
            lastApiResponse = api.changePasswordWithHttpInfo(user.getId(), NEW_PASSWORD);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();

        }catch (ApiException e){
            lastApiResponse = null;
            lastApiCallThrewException = true;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code for the update$")
    public void i_receive_a_status_code_for_the_update(int arg1) throws Throwable {
        assertEquals(200, lastStatusCode);
    }

}
