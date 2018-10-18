package com.senla.hibernate;

import org.hibernate.Session;

import java.util.List;

public interface GenericDAO<T> {

    void add(Session session, T t);

    void delete(Session session, T t);

    T getById(Session session, Long id);

    void update(Session session, T t);

    List<T> getAll(Session session);

    void addAll(Session session, List<T> notExistList);
}
