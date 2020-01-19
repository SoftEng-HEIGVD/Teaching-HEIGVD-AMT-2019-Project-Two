package ch.heigvd.amt.users.api.spec.steps;

import ch.heigvd.amt.users.api.dto.UserLogin;
import ch.heigvd.amt.users.api.dto.UserLogin;
import ch.heigvd.amt.users.api.spec.helpers.Environment;
import ch.heigvd.amt.users.api.DefaultApi;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ch.heigvd.amt.users.ApiException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps {
    private Environment environment;
    private DefaultApi api;

    UserLogin userLogin;
    private String token;

    public LoginSteps(Environment environment){
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^I have a credential payload$")
    public void i_have_a_credential_payload() throws Throwable {
        userLogin = new UserLogin();
        userLogin.setEmail("john.doe@boozify.ch");
        userLogin.setPassword("test");
    }

    @When("^I POST it to the /login endpoint$")
    public void i_POST_it_to_the_login_endpoint() throws Throwable {
        try{
            environment.setApiResponse(api.loginUserWithHttpInfo(userLogin));
            environment.setApiCallThrewException(false);
            environment.setApiException(null);
            environment.setLastStatusCode(environment.getApiResponse().getStatusCode());
            token = environment.getApiResponse().getData().toString();
        } catch (ApiException e){
            environment.setApiCallThrewException(true);
            environment.setApiResponse(null);
            environment.setApiException(e);
            environment.setLastStatusCode(environment.getApiException().getCode());
        }
    }

    @Then("^I receive a (\\d+) status code and a token$")
    public void i_receive_a_status_code_and_a_token(int arg1) throws Throwable {
        assertEquals(arg1, environment.getLastStatusCode());
        assertNotNull(token);
    }

    @Given("^I have a wrong credential payload$")
    public void i_have_a_wrong_credential_payload() throws Throwable {
        userLogin = new UserLogin();
        userLogin.setEmail("test@wrong.ch");
        userLogin.setPassword("wrong");
    }





}
