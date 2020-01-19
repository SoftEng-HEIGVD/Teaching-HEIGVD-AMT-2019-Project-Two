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
public class GetCountriesSteps {

    private Environment environment;
    private DefaultApi api;

    Trip trip;

    public GetCountriesSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I POST it to the /countries endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        try {
            environment.setLastApiResponse(api.getCountriesWithHttpInfo());
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
