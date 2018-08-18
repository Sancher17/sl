package com.senla.mainmodule.services;

import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.repositories.IRepository;
import com.senla.mainmodule.repositories.IRepositoryBook;

import java.util.Date;
import java.util.List;

public interface IServiceBook extends IService {

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

    IRepository getRepositoryBook();

    void markBookOld();
}
