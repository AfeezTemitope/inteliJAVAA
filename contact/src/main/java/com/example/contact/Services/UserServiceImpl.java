package com.example.contact.Services;

import com.example.contact.DTos.request.CreateUserRequest;
import com.example.contact.DTos.request.LoginUserRequest;
import com.example.contact.DTos.response.CreateUserResponse;
import com.example.contact.DTos.response.LoginUserResponse;
import com.example.contact.Exception.InvalidCredentialsException;
import com.example.contact.Exception.UserNotFoundException;
import com.example.contact.data.models.User;
import com.example.contact.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;


    @Override
    public CreateUserResponse registerUser(CreateUserRequest createUserRequest) {
        String username = createUserRequest.getUsername().trim().toLowerCase();
        String password = createUserRequest.getPassword();
        if(username.isEmpty() || password.isEmpty()) throw new IllegalArgumentException("username or password cannot be empty");
        User existingUser = userRepository.findByUsername(username);
        if(existingUser != null) throw new UserNotFoundException("username already exists");

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(hashPassword(password));
        newUser = userRepository.save(newUser);

        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setMessage("registered.SUCCESSFULLY");
        createUserResponse.setUserId(newUser.getId());
        return createUserResponse;

    }

    @Override
    public LoginUserResponse login(LoginUserRequest loginUserRequest) {
    String username = loginUserRequest.getUsername().trim().toLowerCase();
    String password = loginUserRequest.getPassword();
    User user = userRepository.findByUsername(username);
    if(user == null) throw new UserNotFoundException("username does not exist");
    if(validatePassword(password, user.getPassword())){
    LoginUserResponse loginUserResponse = new LoginUserResponse();
    loginUserResponse.setMessage("login.SUCCESSFULLY");
    return loginUserResponse;
    }else{
        throw new InvalidCredentialsException("username or password is incorrect");
    }

    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    private boolean validatePassword(String password, String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, hashedPassword);
    }
}
