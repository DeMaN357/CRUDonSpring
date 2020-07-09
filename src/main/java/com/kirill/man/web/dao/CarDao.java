package com.kirill.man.web.dao;

import com.kirill.man.web.model.User;

import java.util.List;

public interface CarDao {
    List<User> getAllUser();

    void addUser(User user);

    void deleteUser(User user);

    User getUserById(Long id);

    void updateUser(User user1);
}
