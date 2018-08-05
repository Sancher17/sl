package com.senla.mainmodule.repositories.impl;

import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.repositories.IRepositoryBook;
import com.senla.mainmodule.repositories.util.ID;

import java.util.ArrayList;
import java.util.List;

public class RepositoryBook implements IRepositoryBook {

    private static Long lastId = 0L;
    private List<Book> books = new ArrayList<>();
    private static RepositoryBook instance = null;

    public static RepositoryBook getInstance() {
        if (instance == null) {
            instance = new RepositoryBook();
        }
        return instance;
    }

    private RepositoryBook() {
    }

    @Override
    public void add(Book book) {
        lastId = ID.nextId(lastId);
        book.setId(lastId);
        books.add(book);
    }

    @Override
    public void deleteById(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    @Override
    public Book getById(Long id){
        for(Book book: books){
            if(book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }

    @Override
    public Book getBookByName(String name) {
        for (Book book : books) {
            if (name.equals(book.getNameBook())) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }
}
