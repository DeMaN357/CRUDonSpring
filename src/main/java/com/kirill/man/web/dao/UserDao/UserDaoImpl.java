package com.kirill.man.web.dao.UserDao;

import com.kirill.man.web.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user1) {
        entityManager.merge(user1);
    }

    @Override
    public User getUserByUserEmail(String email) {
        return entityManager.createQuery("select user from User user where user.email = : email", User.class).setParameter("email", email).getSingleResult();
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("select user from User user where user.first_name = : name", User.class).setParameter("name", name).getSingleResult();
    }
}
