package com.senla.db;

import entities.Book;

import java.util.List;

public interface IBookDao extends DAO <Book> {

    void setAll(List<Book> entity);

    Book getByName(String name);
}
