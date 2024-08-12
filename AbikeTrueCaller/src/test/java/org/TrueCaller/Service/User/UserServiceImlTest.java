package org.TrueCaller.Service.User;

import org.TrueCaller.data.repository.UserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImlTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testThatUserCanLoginSuccessfully() {
        User user = new User();

    }

    @AfterEach
    void cleanUp(){
      userRepository.deleteAll();
    }
}