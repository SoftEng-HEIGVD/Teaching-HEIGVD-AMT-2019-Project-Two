package heig.vd.ch.projet.travel.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.travel.ApiException;
import heig.vd.ch.projet.travel.api.DefaultApi;
import heig.vd.ch.projet.travel.api.spec.helpers.Environment;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class GetTripsSteps {

    private Environment environment;
    private DefaultApi api;

    public GetTripsSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I GET it to the /trips endpoint with offset (\\d+) and limit (\\d+)$")
    public void i_get_it_to_the_trips_endpoint(Integer offset, Integer limit) throws Throwable {
        try {
            environment.setLastApiResponse(api.getTripsWithHttpInfo(environment.getToken(),offset,limit));
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
