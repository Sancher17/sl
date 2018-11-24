package com.cafe.dao;

import com.cafe.api.dao.IUsersDao;
import com.cafe.dao.util.HibernateUtil;
import com.cafe.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersDao implements IUsersDao {

    public UsersDao() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }

    public List<User> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (List<User>) session.createQuery("from User").list();
    }





}
