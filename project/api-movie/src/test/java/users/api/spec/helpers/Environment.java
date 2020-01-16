package users.api.spec.helpers;

import movies.api.ActorsApi;
import movies.api.MoviesApi;

import java.io.IOException;
import java.util.Properties;

public class Environment {
    private MoviesApi moviesApi = new MoviesApi();
    private ActorsApi actorsApi = new ActorsApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("movies.server.url");
        moviesApi.getApiClient().setBasePath(url);
        actorsApi.getApiClient().setBasePath((url));
    }

    public MoviesApi getApi() {
        return moviesApi;
    }
}
