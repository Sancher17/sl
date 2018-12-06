package com.cafe.api.dao;

import java.util.Date;
import java.util.List;

public interface IGenericDao<T> {

    void add(T t);

    void delete(Long id);

    void update(T t);

    T getById(Long id);

    T getByName(String name);

    List<T> getAll();

    Class<T> getChildClass();

}
