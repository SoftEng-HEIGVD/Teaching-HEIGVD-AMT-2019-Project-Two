package ch.heigvd.videogames.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ch.heigvd.videogames.ApiException;
import ch.heigvd.videogames.ApiResponse;
import ch.heigvd.videogames.api.DefaultApi;
import ch.heigvd.videogames.api.dto.Videogame;
import ch.heigvd.videogames.api.spec.helpers.Environment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationSteps {

    private Environment environment;
    private DefaultApi api;

    Videogame videogame;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^there is a Videogames server$")
    public void there_is_a_Videogames_server() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertNotNull(api);
    }

    @Given("^I have a videogame payload$")
    public void i_have_a_videogame_payload() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        videogame = new ch.heigvd.videogames.api.dto.Videogame();
    }

    @When("^I POST it to the /videogames endpoint$")
    public void i_POST_it_to_the_videogames_endpoint() throws Throwable {
        try {
            lastApiResponse = api.createVideogameWithHttpInfo(videogame);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }

    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(201, lastStatusCode);
    }

}
