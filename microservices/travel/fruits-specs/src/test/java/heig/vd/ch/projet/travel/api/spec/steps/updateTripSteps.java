package heig.vd.ch.projet.travel.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.travel.ApiException;
import heig.vd.ch.projet.travel.api.DefaultApi;
import heig.vd.ch.projet.travel.api.dto.Country;
import heig.vd.ch.projet.travel.api.dto.Reason;
import heig.vd.ch.projet.travel.api.dto.Trip;
import heig.vd.ch.projet.travel.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class updateTripSteps {

    private Environment environment;
    private DefaultApi api;

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJlbWFpbCI6ImFkbWluQGFkbWluLmNoIn0.elXPVmY4-sfOn6Qk4mFt85_gOvMjVrm02FDeAFfp3xU";


    public updateTripSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I PATCH it to the /trips/(\\d+) endpoint$")
    public void i_POST_it_to_the_users_endpoint(Integer idTrip) throws Throwable {
        try {
            environment.setLastApiResponse(api.updateTripWithHttpInfo(token,idTrip,environment.getTrip()));
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
}
