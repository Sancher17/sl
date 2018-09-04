package com.senla.repositories;


import java.util.List;

public interface IRepository<T> {

    void add(T obj);

    void deleteById(Long id);

    T getById(Long id);

    List <T> getAll();



    void setLastId(Long lastId);
}
