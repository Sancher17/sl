package com.senla.repositories.impl;

import com.senla.repositories.IRepositoryBook;
import com.senla.repositories.util.Id;
import entities.Book;

import java.util.ArrayList;
import java.util.Iterator;
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
        lastId = findMaxId();
        lastId = Id.nextId(lastId);
        book.setId(lastId);
        books.add(book);
    }

    private Long findMaxId() {
        Long maxId = 0L;
        for (Book book :  books) {
            if (book.getId() > maxId) {
                maxId = book.getId();
            }
        }
        return maxId;
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

    public void update(Book entity){
        int index;
        Long id = entity.getId();
        for (Book newBook : books) {
            if (newBook.getId().equals(id)) {
                index = books.indexOf(newBook);
                books.set(index, newBook);
            }
        }
    }

    @Override
    public void addAll(List<Book> notExistList) {
        books.addAll(notExistList);
    }

    @Override
    public Book getByName(String name) {
        for (Book book : books) {
            if (name.equals(book.getNameBook())) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> getAll() {
        return  books;
    }

    @Override
    public void setAll(List<Book> books) {
        this.books = books;
    }

}
