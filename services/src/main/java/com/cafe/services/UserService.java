package com.cafe.services;

import com.cafe.api.dao.IGenericDao;
import com.cafe.api.dao.IUserDao;
import com.cafe.api.dtoconverters.IUserConverter;
import com.cafe.api.services.IUserService;
import com.cafe.dto.user.UserDto;
import com.cafe.model.Login;
import com.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    private IGenericDao<User> getDao() {
        return userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        getDao().update(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getDao().delete(id);
    }

    @Transactional
    @Override
    public User getById(Long id) {
        return getDao().getById(id);
    }

    @Transactional
    @Override
    public List<User> getAll() {
        return getDao().getAll();
    }

    @Override
    public User getByName(String name) {
        return getDao().getByName(name);
    }

    @Override
    public User getByLogin(Login login) {
        return userDao.getByLogin(login);
    }

    public User getByNameLogin(String nameLogin) {
        return userDao.getByNameLogin(nameLogin);
    }
}
