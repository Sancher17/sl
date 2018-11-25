package com.cafe.dao;

import com.cafe.api.dao.GenericDao;
import com.cafe.dao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AbstractDao<T> implements GenericDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    public AbstractDao() {
        System.out.println(this.getClass().getSimpleName());
    }

    Session getSession(){
        return sessionFactory.openSession();
    }

    @Override
    public void add(T t) {
        getSession().save(t);
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public T getById(Long id, Class<T> clazz) {
        return getSession().get(clazz, id);
    }

    @Override
    public List<T> getAll(Class clazz) {
        String table = clazz.getSimpleName();
        return getSession().createQuery("select t from " + table + " t", clazz).getResultList();
    }

}
