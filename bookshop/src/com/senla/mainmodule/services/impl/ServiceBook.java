package com.senla.mainmodule.services.impl;

import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.repositories.IRepositoryBook;
import com.senla.mainmodule.repositories.IRepositoryRequest;
import com.senla.mainmodule.repositories.impl.RepositoryBook;
import com.senla.mainmodule.repositories.impl.RepositoryRequest;
import com.senla.mainmodule.services.IServiceBook;
import com.senla.mainmodule.util.comparators.book.*;
import org.apache.log4j.Logger;

import java.util.*;

public class ServiceBook extends Service implements IServiceBook {

    private static final Logger log = Logger.getLogger(ServiceBook.class);
    private IRepositoryBook books = RepositoryBook.getInstance();
    private IRepositoryRequest repositoryRequest = RepositoryRequest.getInstance();

    private static ServiceBook instance = null;

    public static ServiceBook getInstance() {
        if (instance == null) {
            instance = new ServiceBook();
        }
        return instance;
    }

    private ServiceBook() {
    }

    @Override
    public void addBook(String name, Date datePublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable) {
        Book newBook = new Book(name, datePublication, dateAddedBookToStore, price, description, isAvailable);
        books.add(newBook);
        notifyObservers("Добавлена книга: " + newBook);

        //проверка по запросам, если Name книги совпадает то в запрос помечается как выполненный (requireIsCompleted = true)
        for (Request request : repositoryRequest.getRequests()) {
            if (newBook.getNameBook().equals(request.getRequireNameBook())) {
                request.setRequireIsCompleted(true);
            }
        }
    }

    @Override
    public void deleteBookById(Long id) {
        if (books.getById(id) != null) {
            notifyObservers("Удалена книга: " + books.getById(id));
            books.deleteById(id);
        }else {
            notifyObservers("Книги с таким индексом нет !!!");
        }
    }

    @Override
    public void sortByAlphabet() {
        books.getBooks().sort(new ComparatorBookByAlphabet());
        notifyObservers("Книги отсортированы по алфавиту");
    }

    @Override
    public void sortByDatePublication() {
        books.getBooks().sort(new ComparatorBookByDatePublication());
        notifyObservers("Книги отсортированы по дате публикации");
    }

    @Override
    public void sortByPrice() {
        books.getBooks().sort(new ComparatorBookByPrice());
        notifyObservers("Книги отсортированы по цене");
    }

    @Override
    public void sortByAvailability() {
        books.getBooks().sort(new ComparatorBookByAvailability());
        notifyObservers("Книги отсортированы по доступности");
    }

    @Override
    public List<Book> getAll() {
        return books.getBooks();
    }

    @Override
    public Book getBookById(Long id) {
        return books.getById(id);
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        sortByDateAddedToShop();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        Date periodSixMonth = cal.getTime();
        List<Book> bookList = new ArrayList<>();
        for (Book book : books.getBooks()) {
            if (book != null) {
                if (book.getDateAddedBookToStore().before(periodSixMonth)) {
                    bookList.add(book);
                }
            }
        }
        return bookList;
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByPrice() {
        sortByPrice();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        Date periodSixMonth = cal.getTime();
        List<Book> bookList = new ArrayList<>();
        for (Book book : books.getBooks()) {
            if (book != null) {
                if (book.getDateAddedBookToStore().before(periodSixMonth)) {
                    bookList.add(book);
                }
            }
        }
        return bookList;
    }

    @Override
    public String getBookDescriptionById(Long id) {
    if (books.getById(id) != null){
            return books.getById(id).getDescription();
        }
        return null;
    }

    @Override
    public IRepositoryBook getRepositoryBook() {
        return books;
    }

    private void sortByDateAddedToShop() {
        books.getBooks().sort(new ComparatorBookByDateAddedToShop());
        notifyObservers("Книги отсортированы по дате добавления в магазин");
    }
}
