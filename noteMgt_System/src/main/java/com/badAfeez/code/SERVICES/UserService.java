package com.badAfeez.code.SERVICES;

import com.badAfeez.code.DTO.request.CreateUser;
import com.badAfeez.code.DTO.response.IsCreated;

public interface UserService {
    Long countNumberOfNotes();

    IsCreated addUser(CreateUser createUser);
}
