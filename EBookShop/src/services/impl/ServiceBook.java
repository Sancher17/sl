package services.impl;

import entities.Book;
import entities.Request;
import org.apache.log4j.Logger;
import repositories.IRepositoryBook;
import repositories.IRepositoryRequest;
import repositories.impl.RepositoryBook;
import repositories.impl.RepositoryRequest;
import services.IServiceBook;
import util.comparators.book.ComparatorBookByAlphabet;
import util.comparators.book.ComparatorBookByAvailability;
import util.comparators.book.ComparatorBookByDatePublication;
import util.comparators.book.ComparatorBookByPrice;

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
        for (Request request: repositoryRequest.getRequests()) {
            if (newBook.getNameBook().equals(request.getRequireNameBook())){
                request.setRequireIsCompleted(true);
            }
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
    public void deleteBookById(Long id) {
        try {
            notifyObservers("Удалена книга: " + books.getById(id));
            books.deleteById(id);
        } catch (ArrayIndexOutOfBoundsException e) { // TODO: 03.08.2018 заменить на коллекции
            notifyObservers("Книги с таким индексом нет !!!");
        }
    }

    @Override
    public List<Book> getAll() {
        return books.getBooks();
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        sortByDatePublication();
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
        Date periodSixMonth;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        periodSixMonth = cal.getTime();
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
    public Book getBookById(Long id) {
        return books.getById(id);
    }

    @Override
    public String getBookDescriptionById(Long id) {
        return books.getById(id).getDescription();
    }

   @Override
    public IRepositoryBook getRepositoryBook() {
        return books;
    }
}
