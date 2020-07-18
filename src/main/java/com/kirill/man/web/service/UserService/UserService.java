package com.kirill.man.web.service.UserService;

import com.kirill.man.web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    void updateUser(User user);

    User getUserByName(String name);
}
