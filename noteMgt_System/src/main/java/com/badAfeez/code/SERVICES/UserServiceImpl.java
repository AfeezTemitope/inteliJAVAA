package com.badAfeez.code.SERVICES;

import com.badAfeez.code.DATA.models.User;
import com.badAfeez.code.DATA.repository.UserRepository;
import com.badAfeez.code.DTO.request.CreateUser;
import com.badAfeez.code.DTO.response.IsCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    private static Long idGenerator = 0L;
    @Override
    public Long countNumberOfNotes() {
        return userRepository.count();
    }

    @Override
    public IsCreated addUser(CreateUser createUser) {
        User create = new User();
        create.setEmail(createUser.getEmail());
        create.setPassword(createUser.getPassword());
        create.setName(createUser.getName());
        Long id = idGenerator();
        create.setId(id);
        userRepository.save(create);
        IsCreated created = new IsCreated();
        created.setMessage("created.OK");
        return created;
    }

    private Long idGenerator() {
//        Long infinity = Long.MAX_VALUE;
//        for (int i = 1; 1 < infinity; i++) return (long) i++;
//        return infinity;
        return idGenerator++;
    }
}
