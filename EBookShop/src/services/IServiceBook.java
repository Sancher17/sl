package services;

import entities.Book;
import repositories.IRepositoryBook;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface IServiceBook {

    void addBook(String name, Date datePublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable);

    void deleteBookById(Long id);

    List<Book> getAll();

    IRepositoryBook getRepositoryBook();

    Book getBookById(Long id);

    void sortByAlphabet();

    void sortByDatePublication();

    void sortByPrice();

    void sortByAvailability();

    String getBooksPeriodMoreSixMonthByDate();

    String getBooksPeriodMoreSixMonthByPrice();

    String getBookDescriptionById(Long id);
}
