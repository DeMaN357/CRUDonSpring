package com.kirill.man.web.service;

import com.kirill.man.web.dao.CarDao;
import com.kirill.man.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    @Autowired
    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return carDao.getAllUser();
    }

    @Transactional
    @Override
    public void addUser(User user) {
        carDao.addUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        carDao.deleteUser(carDao.getUserById(id));
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return carDao.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        carDao.updateUser(user);
    }
}
