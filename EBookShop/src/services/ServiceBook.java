package services;


import com.danco.training.TextFileWorker;
import data.parse.ParseBook;
import entities.Book;
import entities.Request;
import org.apache.log4j.Logger;
import repositories.RepositoryBook;

import java.util.*;

import static constants.Constants.*;


public class ServiceBook extends Observable implements Service {

    private static final Logger log = Logger.getLogger(ServiceBook.class);
    private String filePath = PATH_BOOK_DATA + "";
    private RepositoryBook books = new RepositoryBook();
    private ParseBook parseBook = new ParseBook(filePath);
    private ServiceRequest requestService;
    private List<Observer> subscribers = new ArrayList<>();

    public ServiceBook(ServiceRequest requestService) {
        this.requestService = requestService;
    }

    public void writeToFile() {
        parseBook.writeObjectToFile(books.getBooks().toArray());
    }

    public void readFromFileFillData(String path) {
        TextFileWorker fileWorker = new TextFileWorker(path);
        String[] tempData = fileWorker.readFromFile();
        Book[] tempBook = new Book[tempData.length];
        for (int i = 0; i < tempData.length; i++) {
            tempBook[i] = parseBook.createObject(tempData[i]);
        }
        books.deleteAll(books.getBooks());//убирает все предыдущие записи в массиве
        List<Book> tempList = new ArrayList<>( Arrays.asList(tempBook));
        books.setBooks(tempList);
    }

    public void addBook(String name, Calendar datePublication, Calendar dateAddedBookToStore, double price, String description) {
        Book newBook = new Book(name, datePublication, dateAddedBookToStore, price, description);
        books.add(newBook);
        notifyObservers("Добавлена книга: " + newBook);

       //проверка по запросам, если Name книги совпадает то в запрос помечается как выполненный (requireIsCompleted = true)
        for (Request request : requestService.getRequests().getRequests()) {
            if (newBook.getNameBook().equals(request.getRequireNameBook())){
                request.setRequireIsCompleted(true);
            }
        }
    }

    public Book getBookByName(String name) {
        for (Book book : books.getBooks()) {
            if (name.equals(book.getNameBook())) {
                return book;
            }
        }
        return null;
    }

    public void sortByAlphabet() {
        Comparator<Book> booksComp = Comparator.comparing(Book::getNameBook);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        books.getBooks().sort(booksComp_nullLast);
        notifyObservers("Книги отсортированы по алфавиту");
    }

    public void sortByDatePublication() {
        Comparator<Book> booksComp = Comparator.comparing(Book::getDateOfPublication);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        books.getBooks().sort(booksComp_nullLast);
        notifyObservers("Книги отсортированы по дате публикации");
    }

    public void sortByPrice() {
        Comparator<Book> booksComp = Comparator.comparing(Book::getPrice);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        books.getBooks().sort(booksComp_nullLast);
        notifyObservers("Книги отсортированы по цене");
    }

    public void sortByAvailability() {
        Comparator<Book> booksComp = Comparator.comparing(Book::isAvailable);
        Comparator<Book> booksComp_nullLast = Comparator.nullsLast(booksComp);
        books.getBooks().sort(booksComp_nullLast);
        notifyObservers("Книги отсортированы по доступности");
    }

    public void deleteBookById(int id) {
        try {
            notifyObservers("Удалена книга: " + books.getBooks().get(id));
            books.deleteById(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            notifyObservers("Книги с таким индексом нет !!!");
        }
    }

    public String printBooks() {
        StringBuilder builder = new StringBuilder();
        for (Book book : books.getBooks()) {
            builder.append(book).append("\n");
        }
        return String.valueOf(builder);
    }

    public String printBooksPeriodMoreSixMonthByDate() {
        sortByDatePublication();
        Calendar periodSixMonth = new GregorianCalendar();
        StringBuilder builder = new StringBuilder();
        periodSixMonth.add(Calendar.MONTH, -6);

        for (Book book : books.getBooks()) {
            if (book != null) {
                if (book.getDateAddedBookToStore().before(periodSixMonth)) {
                    builder.append(book).append("\n");
                }
            }
        }
        if (builder.length() != 0) {
            return String.valueOf(builder);
        }
        return "nothing to show";
    }

    public String printBooksPeriodMoreSixMonthByPrice() {
        sortByPrice();
        StringBuilder builder = new StringBuilder();
        GregorianCalendar periodSixMonth = new GregorianCalendar();
        periodSixMonth.add(Calendar.MONTH, -6);

        for (Book book : books.getBooks()) {
            if (book != null) {
                if (book.getDateAddedBookToStore().before(periodSixMonth)) {
                    builder.append(book).append("\n");
                }
            }
        }
        if (builder.length() != 0) {
            return String.valueOf(builder);
        }
        return "nothing to show";
    }

    public Book getBookById(int id) {
        return books.getBooks().get(id);
    }

    public String getBookDescriptionById(int id) {
        try {
            return books.getById(id).getDescription();
        } catch (NullPointerException e) {
            log.info("Нет книги с данным ID " + e);
            return "Книги с таким ID нет !!!";
        }
    }

    @Override
    public synchronized void addObserver(Observer o) {
        subscribers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        subscribers.remove(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        for (Observer observer : subscribers) {
            System.out.println(arg);
        }
    }

}
