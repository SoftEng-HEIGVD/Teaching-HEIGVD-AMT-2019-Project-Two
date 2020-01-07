package heig.vd.ch.projet.auth.api.spec.helpers;

import heig.vd.ch.projet.auth.ApiException;
import heig.vd.ch.projet.auth.ApiResponse;
import heig.vd.ch.projet.auth.api.DefaultApi;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private DefaultApi api = new DefaultApi();

    public ApiResponse lastApiResponse;
    public ApiException lastApiException;
    public boolean lastApiCallThrewException;
    public int lastStatusCode;

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("heig.vd.ch.projet.auth.server.url");
        api.getApiClient().setBasePath(url);

    }

    public DefaultApi getApi() {
        return api;
    }

    /*public ApiResponse getLastApiResponse() {
        return lastApiResponse;
    }

    public ApiException getLastApiException() {
        return lastApiException;
    }

    public boolean lastApiCallThrewException(){
        return lastApiCallThrewException;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }*/
}
