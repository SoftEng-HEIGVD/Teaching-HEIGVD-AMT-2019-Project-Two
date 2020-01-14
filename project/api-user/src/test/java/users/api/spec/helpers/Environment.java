package users.api.spec.helpers;

import users.api.AdminApi;
import users.api.UserApi;

import java.io.IOException;
import java.util.Properties;

public class Environment {
    private UserApi api = new UserApi();
    private AdminApi adminApi = new AdminApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("users.server.url");
        api.getApiClient().setBasePath(url);
        adminApi.getApiClient().setBasePath((url));
    }

    public UserApi getApi() {
        return api;
    }
}
