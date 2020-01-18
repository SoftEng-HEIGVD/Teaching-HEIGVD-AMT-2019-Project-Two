package spring.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.api.exceptions.ApiException;
import spring.api.exceptions.AuthenticationException;
import spring.api.exceptions.BadRequestException;
import spring.api.exceptions.NotFoundException;
import spring.entities.UserEntity;
import spring.model.JwtToken;
import spring.model.ProfileUpdate;
import spring.model.User;
import spring.repositories.UserRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationService authenticationService;

    @Override
    public URI saveUser(User user) throws BadRequestException {
        if(user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException("Please provide username AND password.");
        }
        // TODO: Other way to check for duplicate entries
        // Check if user with given username already exists
        if(userRepository.findById(user.getUsername()).isPresent()) {
            throw new BadRequestException("User with username " + user.getUsername() + " already exists.");
        }

        UserEntity userEntity = dtoConverter.toUserEntity(user);
        setPassword(userEntity, user.getPassword());
        userRepository.save(userEntity);
        String username = userEntity.getUsername();

        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUri();
    }

    @Override
    public void deleteUser(String username) throws NotFoundException {
        Optional<UserEntity> userEntity = userRepository.findById(username);
        if(!userEntity.isPresent()) {
            throw new NotFoundException(buildNotFoundExceptionMessage(username));
        } else {
            userRepository.deleteById(username);
        }
    }

    @Override
    public List<User> getAllUsers(int page, int pageSize) throws NotFoundException {
        List<User> users = new ArrayList<>();

        for (UserEntity userEntity : userRepository.findAll(PageRequest.of(page, pageSize))) {
            users.add(dtoConverter.toUser(userEntity));
        }

        if(users.isEmpty()) {
            throw new NotFoundException("No users found");
        }
        return users;
    }

    @Override
    public User getUserById(String username) throws NotFoundException {
        return dtoConverter.toUser(userRepository.findById(username)
                .orElseThrow(() -> new NotFoundException(buildNotFoundExceptionMessage(username))));
    }

    public JwtToken authenticateUser(User user) throws ApiException {
        if(user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException("Please provide username AND password.");
        }

        UserEntity userEntity = userRepository.findById(user.getUsername())
                .orElseThrow(() -> new NotFoundException(buildNotFoundExceptionMessage(user.getUsername())));

        // Check password
        if (authenticationService.checkPassword(user.getPassword(), userEntity.getPassword())) {
            String tokenString = jwtUtil.createToken(userEntity.getUsername(), userEntity.isAdmin());
            JwtToken token = new JwtToken();
            token.setToken(tokenString);
            return token;
        } else {
            throw new AuthenticationException("Wrong username or password");
        }
    }

    @Override
    public void changePassword(String username, String newPassword) throws NotFoundException, BadRequestException {
        UserEntity userEntity = userRepository.findById(username)
                .orElseThrow(() -> new NotFoundException(buildNotFoundExceptionMessage(username)));

        setPassword(userEntity, newPassword);
        userRepository.save(userEntity);
    }

    @Override
    public void updateProfile(String username, ProfileUpdate profileUpdate) throws NotFoundException, BadRequestException {
        UserEntity userEntity = userRepository.findById(username)
                .orElseThrow(() -> new NotFoundException(buildNotFoundExceptionMessage(username)));

        userEntity.setEmail(profileUpdate.getEmail());
        userEntity.setFirstName(profileUpdate.getFirstName());
        userEntity.setLastName(profileUpdate.getLastName());
        userRepository.save(userEntity);
    }

    private void setPassword(UserEntity userEntity, String password) throws BadRequestException {
        // Check length of password
        if(password.length() < 6) {
            throw new BadRequestException("Please choose a password with at least 6 characters.");
        }
        userEntity.setPassword(authenticationService.hashPassword(password));
    }

    @Override
    public void makeAdmin() {
        UserEntity adminEntity = UserEntity.builder().username("admin")
                .password(authenticationService.hashPassword("root"))
                .isAdmin(true).build();
        userRepository.save(adminEntity);
    }

    @Override
    public void makeTestUser() {
        UserEntity userEntity = UserEntity.builder().username("user1")
                .password(authenticationService.hashPassword("password"))
                .isAdmin(false).build();
        userRepository.save(userEntity);
    }

    /**
     * Utility method to construct the not found exception message uniformly.
     * @return formatted exception message
     */
    private String buildNotFoundExceptionMessage(String username) {
        if(username == null || username.isEmpty()) {
            // default message
            return "User not found";
        } else {
            return "User with username " + username + " not found";
        }
    }
}
