package spring.api.services;

import spring.api.exceptions.ApiException;
import spring.api.exceptions.AuthenticationException;
import spring.api.exceptions.BadRequestException;
import spring.api.exceptions.NotFoundException;
import spring.model.JwtToken;
import spring.model.User;

import java.net.URI;
import java.util.List;

public interface UserService {

    /**
     * Convert to entity and save to db.
     * @param user the user dto
     * @return Uri location of the newly created user resource in the api
     * @throws BadRequestException
     */
    URI saveUser(User user) throws BadRequestException;

    /**
     * Delete user from db by username
     * @param username username of the user
     */
    void deleteUser(String username) throws NotFoundException;

    /**
     * Get all user in db
     * @return the list of users
     * @throws NotFoundException if no users found
     */
    List<User> getAllUsers() throws NotFoundException;

    /**
     * Get a user by his username
     * @param username
     * @return user
     * @throws NotFoundException if no user with username provided
     */
    User getUserById(String username) throws NotFoundException;

    /**
     * Authenticate user with credentials provided and return jwt token.
     * @param user the user dto containing his credentials
     * @return the jwt token when authenticated
     * @throws NotFoundException
     * @throws AuthenticationException
     */
    JwtToken authenticateUser(User user) throws ApiException;

    /**
     * Method reserved only for the super user system administrator. Used to initialize the admin entity in the db.
     */
    void makeAdmin();
}
