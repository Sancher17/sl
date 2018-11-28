package com.cafe.services;

import com.cafe.api.dao.IUserDao;
import com.cafe.api.services.IUsersService;
import com.cafe.model.Login;
import com.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersService implements IUsersService {

    @Autowired
    private IUserDao usersDao;

    public UsersService() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }


    @Override
    public void add(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return usersDao.getAll(User.class);
    }

    
    @Override
    public User validateUser(Login login) {
        return usersDao.validateUser(login);
    }
}
