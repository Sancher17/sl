package com.cafe.api.dao;

import org.hibernate.Session;

import java.util.List;

public interface GenericDao<T> {

    default List<T> getAll(Session session, Class<T> clazz) {
        String table = clazz.getSimpleName();
        return session.createQuery("select t from " + table + " t", clazz).getResultList();
    }

}
