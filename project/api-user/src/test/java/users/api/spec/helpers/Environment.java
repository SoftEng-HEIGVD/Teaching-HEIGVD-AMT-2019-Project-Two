package users.api.spec.helpers;

import okhttp3.OkHttpClient;
import users.ApiException;
import users.ApiResponse;
import users.api.AdminApi;
import users.api.UserApi;
import users.api.dto.JwtToken;
import users.api.dto.User;

import java.io.IOException;
import java.util.Properties;

public class Environment {
    private UserApi userApi = new UserApi();
    private AdminApi adminApi = new AdminApi();

    private OkHttpClient client = new OkHttpClient();

    private User admin;
    private JwtToken adminToken;

    private User testUser;
    private JwtToken lastJwtToken;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("users.server.url");
        userApi.getApiClient().setBasePath(url);
        adminApi.getApiClient().setBasePath((url));

        this.admin = new User();
        this.admin.setUsername("admin");
        this.admin.setPassword("root");
    }

    public UserApi getUserApi() {
        return userApi;
    }

    public AdminApi getAdminApi() {
        return adminApi;
    }

    public OkHttpClient getHttpClient() {
        return client;
    }

    public ApiException getLastApiException() {
        return lastApiException;
    }

    public ApiResponse getLastApiResponse() {
        return lastApiResponse;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public boolean isLastApiCallThrewException() {
        return lastApiCallThrewException;
    }

    public void setLastApiResponse(ApiResponse lastApiResponse) {
        this.lastApiResponse = lastApiResponse;
    }

    public void setLastApiException(ApiException lastApiException) {
        this.lastApiException = lastApiException;
    }

    public void setLastApiCallThrewException(boolean lastApiCallThrewException) {
        this.lastApiCallThrewException = lastApiCallThrewException;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    public User getTestUser() {
        return testUser;
    }

    public void setTestUser(User testUser) {
        this.testUser = testUser;
    }

    public JwtToken getLastJwtToken() {
        return lastJwtToken;
    }

    public void setLastJwtToken(JwtToken lastJwtToken) {
        this.lastJwtToken = lastJwtToken;
    }

    public User getAdmin() {
        return admin;
    }

    public JwtToken getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(JwtToken adminToken) {
        this.adminToken = adminToken;
    }
}
