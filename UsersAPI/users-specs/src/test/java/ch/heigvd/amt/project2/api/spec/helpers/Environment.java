package ch.heigvd.amt.project2.api.spec.helpers;

import ch.heigvd.amt.project2.api.UserApi;

import java.io.IOException;
import java.util.Properties;

/**
 * Based on the Environment class of Fruits, written by Olivier Liechti
 * @author Nathanaël Mizutani
 */
public class Environment {

    private UserApi api = new UserApi();

    public Environment() throws IOException {
        Properties properties = new Properties();

        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.project2.server.url");
        api.getApiClient().setBasePath(url);
    }

    public UserApi getApi(){
        return api;
    }
}
