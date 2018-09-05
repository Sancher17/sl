package com.senla.services;

import entities.Book;

import java.util.Date;
import java.util.List;

public interface IServiceBook extends IService <Book> {

//    void addBook(String name, Date datePublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable);
    void addBook(Book book);

    void deleteBookById(Long id);

    List<Book> sortByAlphabet();

    List<Book> sortByDatePublication();

    List<Book> sortByPrice();

    List<Book> sortByAvailability();

    List<Book> getAll();

    List<Book> getBooksPeriodMoreSixMonthByDate();

    List<Book> getBooksPeriodMoreSixMonthByPrice();

    String getBookDescriptionById(Long id);

    void markBookOld();

    Book getBookById(Long id);
}
