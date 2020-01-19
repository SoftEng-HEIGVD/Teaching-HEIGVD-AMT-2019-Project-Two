package ch.heigvd.amt.users.api.spec.steps;

import ch.heigvd.amt.users.ApiException;
import ch.heigvd.amt.users.api.DefaultApi;
import ch.heigvd.amt.users.ApiResponse;
import ch.heigvd.amt.users.api.DefaultApi;
import ch.heigvd.amt.users.api.dto.InlineObject;
import ch.heigvd.amt.users.api.dto.User;
import ch.heigvd.amt.users.api.spec.helpers.Environment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class UsersSteps {

    private Environment environment;
    private DefaultApi api;

    private String token;
    User userToken;
    User user;
    InlineObject password;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public UsersSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
        this.userToken = new User();
        userToken.setEmail("john.doe@boozify.ch");
        userToken.setAdministrator(true);
    }

    private String createTokenForTest(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date currentDate = new Date();
        Date expirationTime = new Date(currentDate.getTime() + (24 * 3600 * 1000));


        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secret");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setSubject(userToken.getEmail()).claim("administrator", userToken.getAdministrator())
                .setIssuedAt(currentDate).setExpiration(expirationTime).signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    @Given("^I have a user payload and a JWT token$")
    public void i_have_a_user_payload_and_a_JWT_token() throws Throwable {
        this.user = new User();
        this.user.setEmail("test@boozify.ch");
        this.user.setFirstName("test");
        this.user.setLastName("test");
        this.user.setPassword("test");
        this.user.setAdministrator(true);
        this.token = createTokenForTest();
        api.getApiClient().setApiKey("Bearer " + this.token);
    }

    @When("^I POST it to the /users endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        try{
            environment.setApiResponse(api.createUserWithHttpInfo(user));
            environment.setApiCallThrewException(false);
            environment.setApiException(null);
            environment.setLastStatusCode(environment.getApiResponse().getStatusCode());
        } catch (ApiException e){
            environment.setApiCallThrewException(true);
            environment.setApiResponse(null);
            environment.setApiException(e);
            environment.setLastStatusCode(environment.getApiException().getCode());
        }
    }

    @Given("^I have a password payload and a JWT token$")
    public void i_have_a_password_payload_and_a_JWT_token() throws Throwable {
        this.password = new InlineObject();
        this.password.setPassword("test");
        this.token = createTokenForTest();
        api.getApiClient().setApiKey("Bearer " + this.token);
    }

    @When("^I PATCH it to the /users/john\\.doe@boozify\\.ch endpoint$")
    public void i_PATCH_it_to_the_users_john_doe_boozify_ch_endpoint(String arg1) throws Throwable {
        try{
            environment.setApiResponse(api.updateUserWithHttpInfo(arg1, this.password));
            environment.setApiCallThrewException(false);
            environment.setApiException(null);
            environment.setLastStatusCode(environment.getApiResponse().getStatusCode());
        } catch (ApiException e){
            environment.setApiCallThrewException(true);
            environment.setApiResponse(null);
            environment.setApiException(e);
            environment.setLastStatusCode(environment.getApiException().getCode());
        }
    }



}
