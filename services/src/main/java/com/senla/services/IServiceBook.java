package com.senla.services;

import entities.Book;

import java.util.Date;
import java.util.List;

public interface IServiceBook extends IService <Book> {

    void addBook(String name, Date datePublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable);

    void deleteBookById(Long id);

    void sortByAlphabet();

    void sortByDatePublication();

    void sortByPrice();

    void sortByAvailability();

    List<Book> getAll();

    Book getByName(String name);

    List<Book> getBooksPeriodMoreSixMonthByDate();

    List<Book> getBooksPeriodMoreSixMonthByPrice();

    String getBookDescriptionById(Long id);

    void markBookOld();
}
