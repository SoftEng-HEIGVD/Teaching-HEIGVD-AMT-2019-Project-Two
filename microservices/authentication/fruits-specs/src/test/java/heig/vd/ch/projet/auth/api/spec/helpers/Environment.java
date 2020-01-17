package heig.vd.ch.projet.auth.api.spec.helpers;

import heig.vd.ch.projet.auth.ApiException;
import heig.vd.ch.projet.auth.ApiResponse;
import heig.vd.ch.projet.auth.api.DefaultApi;
import heig.vd.ch.projet.auth.api.dto.AuthDTO;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private DefaultApi api = new DefaultApi();

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private String token;
    private AuthDTO authDTO;


    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("heig.vd.ch.projet.auth.server.url");
        api.getApiClient().setBasePath(url);

    }

    public DefaultApi getApi() {
        return api;
    }

    public ApiResponse getLastApiResponse() {
        return lastApiResponse;
    }

    public void setLastApiResponse(ApiResponse lastApiResponse) {
        this.lastApiResponse = lastApiResponse;
    }

    public ApiException getLastApiException() {
        return lastApiException;
    }

    public void setLastApiException(ApiException lastApiException) {
        this.lastApiException = lastApiException;
    }

    public boolean lastApiCallThrewException(){
        return lastApiCallThrewException;
    }

    public void setLastApiCallThrewException(boolean lastApiCallThrewException) {
        this.lastApiCallThrewException = lastApiCallThrewException;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }


    public AuthDTO getAuthDTO() {
        return authDTO;
    }

    public void setAuthDTO(AuthDTO authDTO) {
        this.authDTO = authDTO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
