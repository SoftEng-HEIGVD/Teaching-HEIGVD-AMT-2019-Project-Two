package heig.vd.ch.projet.travel.api.spec.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.travel.ApiException;
import heig.vd.ch.projet.travel.api.DefaultApi;
import heig.vd.ch.projet.travel.api.dto.TripDTO;
import heig.vd.ch.projet.travel.api.spec.helpers.Environment;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class deleteTripSteps {

    private Environment environment;
    private DefaultApi api;

    Integer idTrip;
    public deleteTripSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I DELETE it to the /trips/idTrip endpoint$")
    public void i_delete_it_to_the_users_endpoint() {
        try {
            TripDTO tripDTO = (TripDTO)environment.getLastApiResponse().getData();
            idTrip = tripDTO.getIdTrip();

            environment.setLastApiResponse(api.deleteTripWithHttpInfo(environment.getToken(),idTrip));
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

    @Then("^I deleted successfully a trip$")
    public void i_deleted_sucessfully_a_trip() {
        try {
            environment.setLastApiResponse(api.getTripByIdWithHttpInfo(environment.getToken(),idTrip));
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
