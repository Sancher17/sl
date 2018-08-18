package com.senla.mainmodule.repositories;

import java.util.List;

public interface IRepository<T> {

    void add(T entity);

    void deleteById(Long id);

    T getById(Long id);

    List <T> getAll();

    void setAll(List<T> entity);

    void setLastId(Long lastId);
}
