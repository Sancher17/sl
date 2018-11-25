package com.cafe.services;

import com.cafe.api.dao.IUsersDao;
import com.cafe.api.services.IUsersService;
import com.cafe.dao.util.HibernateUtil;
import com.cafe.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersService implements IUsersService {

    @Autowired
    private IUsersDao usersDao;

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
}
