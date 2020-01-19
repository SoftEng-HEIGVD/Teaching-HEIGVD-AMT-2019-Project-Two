package heig.vd.ch.projet.travel.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.travel.api.dto.Country;
import heig.vd.ch.projet.travel.api.dto.Reason;
import heig.vd.ch.projet.travel.api.dto.Trip;
import heig.vd.ch.projet.travel.api.dto.TripDTO;
import heig.vd.ch.projet.travel.api.spec.helpers.Environment;
import heig.vd.ch.projet.travel.ApiException;
import heig.vd.ch.projet.travel.api.DefaultApi;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationTripSteps {

    private Environment environment;
    private DefaultApi api;

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJlbWFpbCI6ImFkbWluQGFkbWluLmNoIn0.elXPVmY4-sfOn6Qk4mFt85_gOvMjVrm02FDeAFfp3xU";


    public CreationTripSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^there is a travel server$")
    public void there_is_a_Users_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a trip payload$")
    public void i_have_a_user_payload() throws Throwable {
        Trip trip = new Trip();

        Reason reason = new Reason();
        reason.setIdReason(5);
        reason.setName("City life");

        Country country = new Country();
        country.setIdCountry(55);
        country.setName("Croatia (Hrvatska)");

        trip.setReason(reason);
        trip.setCountry(country);
        trip.setVisited(true);
        trip.setDate("2020-01-23");

        environment.setTrip(trip);
    }

    @When("^I POST it to the /trips endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        try {
            environment.setLastApiResponse(api.createTripWithHttpInfo(token,environment.getTrip()));
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

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int code) throws Throwable {
        assertEquals(code, environment.getLastStatusCode());
    }

    @Then("^I added successfully a trip$")
    public void i_added_successfully_a_trip() {
        TripDTO tripDTO = (TripDTO) environment.getLastApiResponse().getData();
        assertNotNull(tripDTO);
    }
}
