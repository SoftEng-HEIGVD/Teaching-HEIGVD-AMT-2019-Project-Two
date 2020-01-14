package spring.api.services;

public interface AuthenticationService {
    String hashPassword(String plainTextPassword);
    boolean checkPassword(String plainTextPassword, String hashedPassword);
}
