package heig.vd.ch.projet.travel.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import heig.vd.ch.projet.travel.ApiException;
import heig.vd.ch.projet.travel.api.DefaultApi;
import heig.vd.ch.projet.travel.api.spec.helpers.Environment;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class GetTripByIdSteps {

    private Environment environment;
    private DefaultApi api;

    public GetTripByIdSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I GET it to the /trips/(\\d+) endpoint$")
    public void i_get_it_to_the_users_endpoint(Integer idTrip) throws Throwable {
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

    @Given("^I have a admin token$")
    public void i_have_a_admin_token() {
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJlbWFpbCI6ImFkbWluQGFkbWluLmNoIn0.elXPVmY4-sfOn6Qk4mFt85_gOvMjVrm02FDeAFfp3xU";
        environment.setToken(token);
    }

    @Given("^I have a user token$")
    public void i_have_a_user_token() {
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoidXNlciIsImVtYWlsIjoidXNlckB1c2VyLmNoIn0.u0d96sUcwllbF6QkvC2XLqKGO3n_tEcPqxBj3efT3r0";
        environment.setToken(token);
    }
}
