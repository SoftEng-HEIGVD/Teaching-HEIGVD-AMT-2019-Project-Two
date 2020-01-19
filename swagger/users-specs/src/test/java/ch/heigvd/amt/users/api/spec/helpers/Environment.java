package ch.heigvd.amt.users.api.spec.helpers;

import ch.heigvd.amt.users.api.DefaultApi;
import ch.heigvd.amt.users.ApiResponse;
import ch.heigvd.amt.users.ApiResponse;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private DefaultApi api = new DefaultApi();
    private ApiResponse apiResponse;
    private ApiException apiException;
    private String token;
    private boolean apiCallThrewException;
    private int lastStatusCode;

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.users.server.url");
        api.getApiClient().setBasePath(url);

    }

    public DefaultApi getApi() {
        return api;
    }

    public void setApi(DefaultApi api){
        this.api = api;
    }

    public ApiResponse getApiResponse(){
        return apiResponse;
    }

    public void setApiResponse(ApiResponse apiResponse){
        this.apiResponse = apiResponse;
    }

    public ApiException getApiException(){
        return apiException;
    }

    public void setApiException(ApiException apiException){
        this.apiException = apiException;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public boolean isApiCallThrewException(){
        return apiCallThrewException;
    }

    public void setApiCallThrewException(boolean apiCallThrewException){
        this.apiCallThrewException = apiCallThrewException;
    }

    public int getLastStatusCode(){
        return lastStatusCode;
    }

    public void setLastStatusCode(int lastStatusCode){
        this.lastStatusCode = lastStatusCode;
    }
}
