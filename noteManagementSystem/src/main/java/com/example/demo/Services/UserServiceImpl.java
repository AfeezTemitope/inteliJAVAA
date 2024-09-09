package com.example.demo.Services;

import com.example.demo.DTO.request.CreateUserRequest;
import com.example.demo.DTO.response.CreateUserResponse;
import com.example.demo.EXCEPTION.PasswordException;
import com.example.demo.EXCEPTION.UserMustNotBeEmpty;
import com.example.demo.EXCEPTION.UserNotFound;
import com.example.demo.data.models.User;
import com.example.demo.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public long countNumberOfUser() {
        return userRepository.count();
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        String username = createUserRequest.getUsername();
        validateUsername(username);
        String password = createUserRequest.getPassword();
        normalizePassword(password);
        String normalizedUsername = normalizeUsername(username);
        User existingUser = userRepository.findUserByUsername(normalizedUsername);
        if(existingUser != null) throw new UserNotFound("username already exists");
        User user = new User();
        user.setUsername(normalizedUsername);
        String hashed = passwordEncoder.encode(password);
        user.setPassword(hashed);
        user=userRepository.save(user);
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setMessage("created.OK");
        createUserResponse.setId(user.getId());
        return createUserResponse;
    }

    @Override
    public User login(String username, String password) {
        String normalizedUsername = normalizeUsername(username);
        User existingUser = userRepository.findUserByUsername(normalizedUsername);
        if(existingUser == null) throw new UserNotFound("username not found");
        if(!passwordEncoder.matches(password, existingUser.getPassword())) throw new PasswordException("password does not match");
        return existingUser;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    private void validateUsername(String username) {
        if(username == null || username.trim().isEmpty()) throw new UserMustNotBeEmpty("Username is empty");
    }
    private String normalizeUsername(String username) {
        return username != null ? username.trim().toLowerCase() : null;
    }
    private void normalizePassword(String password) {
        int MIN_PASSWORD_LENGTH = 6;
        if(password == null || password.trim().isEmpty()) throw new PasswordException("Password is empty");
        if(password.length() < MIN_PASSWORD_LENGTH) throw new PasswordException("password must be at least 6 digit long ");
    }
}
