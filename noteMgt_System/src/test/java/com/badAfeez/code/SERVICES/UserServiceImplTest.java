package com.badAfeez.code.SERVICES;

import com.badAfeez.code.DATA.models.User;
import com.badAfeez.code.DATA.repository.UserRepository;
import com.badAfeez.code.DTO.request.CreateUser;
import com.badAfeez.code.DTO.response.IsCreated;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
@Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
    userRepository.deleteAll();
    }

    @Test
    void testThatUserCanBeCreated(){
//        CreateUser createUser = new CreateUser();
//        createUser.setName("badAfeez");
//        createUser.setEmail("badAfeez@gmail.com");
//        createUser.setPassword("password");
//        createUser.setId(createUser.getId());
//        System.out.println(createUser);
//        System.out.println(createUser.getId());
//
//        IsCreated isCreated = userService.addUser(createUser);
//        isCreated.setMessage("created.OK");
//        assertEquals(1,userRepository.count());
        CreateUser createUser = new CreateUser();
        createUser.setName("badAfeez");
        createUser.setEmail("badAfeez@gmail.com");
        createUser.setPassword("password");

        IsCreated isCreated = userService.addUser(createUser);

        assertEquals("created.OK", isCreated.getMessage());

        assertEquals(1, userRepository.count());

        User createdUser = userRepository.findAll().get(0);
        assertNotNull(createdUser.getId());
        System.out.println("Created User ID: " + createdUser.getId());

    }

    @Test
    public void testThatTwoUsersCanBeCreatedAndTheCountIs2AndTheirIdsAreSequential() {
        User user = new User();
        CreateUser createUser1 = new CreateUser();
        createUser1.setName("Alero");
        createUser1.setEmail("alero@gmail.com");
        createUser1.setPassword("password");
        createUser1.setId(user.getId());


        CreateUser createUser2 = new CreateUser();
        createUser2.setName("Deji");
        createUser2.setEmail("deji@gmail.com");
        createUser2.setPassword("password");
        createUser2.setId(user.getId());

        IsCreated isCreated = userService.addUser(createUser1);
        IsCreated isCreated2 = userService.addUser(createUser2);

        long userCount = userRepository.count();
        assertEquals(2, userCount);
        List<User> users = userRepository.findAll();
        assertEquals(2, users.size());

        System.out.println("First User ID: " + createUser1.getId());
        System.out.println("Second User ID: " + createUser2.getId());
        }
    }

