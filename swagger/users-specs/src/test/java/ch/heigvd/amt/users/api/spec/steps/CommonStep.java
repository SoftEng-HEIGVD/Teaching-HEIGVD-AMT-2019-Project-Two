package ch.heigvd.amt.users.api.spec.steps;

import ch.heigvd.amt.users.api.DefaultApi;
import ch.heigvd.amt.users.api.spec.helpers.Environment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommonStep {

    private Environment environment;
    private DefaultApi api;

    @Given("^there is an login server$")
    public void there_is_an_login_server() throws Throwable {
        assertNotNull(api);
    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, environment.getLastStatusCode());
    }

}
