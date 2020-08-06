package com.kirill.man.web.dao.UserDao;

import com.kirill.man.web.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    Set<User> getAllUser();

    void addUser(User user);

    void deleteUser(User user);

    User getUserById(Long id);

    void updateUser(User user1);

    User getUserByUserEmail(String email);

    User getUserByName(String name);
}
