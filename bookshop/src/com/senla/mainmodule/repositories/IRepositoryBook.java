package com.senla.mainmodule.repositories;

import com.senla.mainmodule.entities.Book;

import java.util.List;

public interface IRepositoryBook {

    void add(Book book);

    void deleteById(Long id);

    Book getById(Long id);

    Book getBookByName(String name);

    List<Book> getBooks();

    void setBooks(List<Book> books);

    void setLastId(Long lastId);
}
