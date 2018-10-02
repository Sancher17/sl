package com.senla.db;

import java.util.List;

public interface DAO <T> {

    void add(T obj);

    void deleteById(Long id);

    T getById(Long id);

    void update(T t);

    List<T> getAll();

    void addAll(List<T> notExistList);

}
