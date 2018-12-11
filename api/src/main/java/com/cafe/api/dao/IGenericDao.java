package com.cafe.api.dao;

import com.cafe.model.GenericEntity;

import java.util.Date;
import java.util.List;

public interface IGenericDao<T extends GenericEntity> {

    void add(T t);

    void delete(Long id);

    void update(T t);

    T getById(Long id);

    T getByName(String name);

    List<T> getAll();

//    Class<T> getChildClass();
    
}
