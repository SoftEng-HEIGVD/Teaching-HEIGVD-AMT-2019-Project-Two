package users.api.spec.steps.movies;

import cucumber.api.java.en.When;
import movies.ApiException;
import movies.api.MoviesApi;
import users.api.spec.helpers.Environment;

public class DeletionSteps {
    private Environment environment;
    private MoviesApi moviesApi;

    public DeletionSteps(Environment environment) {
        this.environment = environment;
        this.moviesApi = this.environment.getMoviesApi();
    }


    @When("^I DELETE it in the /movies/moviesId endpoint$")
    public void iDELETEItInTheMoviesMoviesIdEndpoint() {
        try {
            this.environment.setLastApiResponse(this.moviesApi.deleteMovieWithHttpInfo(this.environment.retrieveLastCreatedResourceId()));
            this.environment.setLastApiCallThrewException(false);
            this.environment.setLastApiException(null);
            this.environment.setLastStatusCode(this.environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            this.environment.setLastApiResponse(null);
            this.environment.setLastApiCallThrewException(true);
            this.environment.setLastApiException(e);
            this.environment.setLastStatusCode(this.environment.getLastApiException().getCode());
        }
    }
}
