package com.cafe.api.dao;

import java.util.List;

public interface GenericDao<T> {

    void add(T t);

    void delete(T t);

    void update(T t);

    T getById(Long id, Class<T> clazz);

    List<T> getAll(Class clazz);
}
