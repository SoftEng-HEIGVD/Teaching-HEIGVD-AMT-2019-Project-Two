package ch.heigvd.amt.project2.users.api.spec.helpers;

import ch.heigvd.amt.project2.api.UserApi;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 * Modified by Nathanaël Mizutani
 */
public class Environment {

    private UserApi api = new UserApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heigvd.amt.project2.users.server.url");
        api.getApiClient().setBasePath(url);

    }

    public UserApi getApi() {
        return api;
    }


}
