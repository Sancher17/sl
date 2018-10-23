package com.senla.api.dao;

import org.hibernate.Session;

import java.util.List;

public interface IGenericDao<T> {

    default void add(Session session, T t) {
        session.save(t);
    }

    default void delete(Session session, T t) {
        session.delete(t);
    }

    default void update(Session session, T t) {
        session.update(t);
    }

    default void addAll(Session session, List<T> list) {
        for (T t : list) {
            add(session, t);
        }
    }

    default List<T> getAll(Session session, Class<T> clazz) {
        String table = clazz.getSimpleName();
        return session.createQuery("select t from " + table + " t", clazz).getResultList();
    }

    default T getById(Session session, Long id, Class<T> clazz){
        return session.get(clazz, id);
    }
}
