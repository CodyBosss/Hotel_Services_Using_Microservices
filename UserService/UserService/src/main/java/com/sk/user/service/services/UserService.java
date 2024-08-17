package com.sk.user.service.services;

import com.sk.user.service.entities.User;

import java.util.List;

public interface UserService {

//    user operation

//    create

    User saveUser(User user);

//    get all user
    List<User> getAllUser();

//    get single user of given userId

    User getUser(String userId);

//    ToDo: update
//    toDo: Delete

}
