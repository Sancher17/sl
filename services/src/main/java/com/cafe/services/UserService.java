package com.cafe.services;

import com.cafe.api.dao.IUserDao;
import com.cafe.api.services.IUserService;
import com.cafe.model.Login;
import com.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Transactional
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public User getByLogin(Login login) {
        return userDao.getByLogin(login);
    }

    public User getByNameLogin(String nameLogin) {
        return userDao.getByNameLogin(nameLogin);
    }
}
