package users.api.spec.steps;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import users.ApiException;
import users.ApiResponse;
import users.api.UserApi;
import users.api.dto.JwtToken;
import users.api.dto.ProfileUpdate;
import users.api.dto.User;
import users.api.spec.helpers.Environment;

import static org.junit.Assert.*;

public class UpdateSteps {

    private static Logger log = LoggerFactory.getLogger(CreationSteps.class);

    private Environment environment;
    private UserApi userApi;

    private JwtToken jwttoken;

    private ProfileUpdate profileUpdate;

    private String newEmail = "new@email.com";
    private String newFirstName = "Pitts";

    private Gson gson;

    public UpdateSteps(Environment environment) {
        this.environment = environment;
        this.userApi = this.environment.getUserApi();
        this.gson = new Gson();
    }

    @Given("^I have a jwt token$")
    public void iHaveAJwtToken() {
        this.userApi.getApiClient().setApiKey("Bearer " + this.environment.getLastJwtToken().getToken());
    }

    @Given("^I have a profile update$")
    public void iHaveAProfileUpdate() {
        this.profileUpdate = new ProfileUpdate();
        this.profileUpdate.setEmail(newEmail);
        this.profileUpdate.setFirstName(newFirstName);
    }

    @When("^I PUT a profile update to the /profileUpdates endpoint$")
    public void iPUTAProfileUpdateToTheUsersUsernameEndpoint() {
        try {
            this.environment.setLastApiResponse(userApi.updateProfileWithHttpInfo(this.profileUpdate));
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

    @When("^I GET the user$")
    public void iGETTheUser() {
        try {
            this.environment.setLastApiResponse(userApi.getUserProfileWithHttpInfo());
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

    @Then("^I have an updated user$")
    public void iHaveAnUpdatedUser() {
        ApiResponse response = this.environment.getLastApiResponse();
        String data = response.getData().toString();
        // VERY UGLY but works for now
        String firstNameExtract = data.substring(data.indexOf("firstName=") + "firstName=".length());
        assertTrue(firstNameExtract.startsWith(newFirstName));
        String emailExtract = data.substring(data.indexOf("email=") + "email=".length());
        assertTrue(emailExtract.startsWith(newEmail));
        // extract the data without the password, because the hashed password
        // causes problems when parsing the json data (ugly solution but works).
        /*JsonObject jsonObject = gson.fromJson(data.substring(0, data.indexOf("password") - 3), JsonObject.class);
        assertEquals(jsonObject.get("firstName").getAsString(), newFirstName);
        assertEquals(jsonObject.get("email").getAsString(), newEmail);*/
    }
}
