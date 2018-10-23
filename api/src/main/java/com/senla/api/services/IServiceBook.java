package com.senla.api.services;

import entities.Book;

import java.util.List;

public interface IServiceBook extends IService <Book> {

    void addBook(Book book);

    void deleteBookById(Long id);

    List<Book> getAll();

    List<Book> getBooksSortedByAlphabet();

    List<Book> getBooksSortedByDatePublication();

    List<Book> getBooksSortedByPrice();

    List<Book> getBooksSortedByAvailability();

    List<Book> getBooksPeriodMoreSixMonthByDate();

    List<Book> getBooksPeriodMoreSixMonthByPrice();

    String getBookDescriptionById(Long id);

    Book getBookById(Long id);

    void markBookOld();
}
