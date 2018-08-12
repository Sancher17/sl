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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

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
        Boolean isOld = false;
        Book newBook = new Book(name, datePublication, dateAddedBookToStore, price, description, isAvailable, isOld);
        books.add(newBook);
        notifyObservers("Добавлена книга: " + newBook);

        if (ALLOW_MARK_REQUESTS){
            for (Request request : repositoryRequest.getRequests()) {
                if (newBook.getNameBook().equals(request.getRequireNameBook())) {
                    request.setRequireIsCompleted(true);
                }
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

    @Override
    public void markBookOld(){
        if (BOOK_IS_OLD != null){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -BOOK_IS_OLD);
            Date markOld = cal.getTime();
            for(Book book: books.getBooks()){
                if (book.getDateAddedBookToStore().before(markOld)){
                    book.setOld(true);
                }
            }
        }
    }

    @Override
    public List<Book> getRepo() {
        return books.getBooks();
    }

    @Override
    public void setRepo(List list) {
        books.setBooks(list);
        setLastId();
    }

    @Override
    public void setLastId() {
        Long id = 0L;
        for (Book book: books.getBooks()){
            if (book.getId() > id){
                id = book.getId();
            }
        }
        books.setLastId(id);
    }

    private void sortByDateAddedToShop() {
        books.getBooks().sort(new ComparatorBookByDateAddedToShop());
        notifyObservers("Книги отсортированы по дате добавления в магазин");
    }
}
