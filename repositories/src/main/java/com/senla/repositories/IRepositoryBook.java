package com.senla.repositories;

import entities.Book;

import java.util.List;


public interface IRepositoryBook  extends IRepository<Book> {

    void setAll(List<Book> entity);

    Book getByName(String name);
}
