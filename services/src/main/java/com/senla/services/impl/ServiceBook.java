package com.senla.services.impl;

import com.senla.fileworker.imports.ImportBookFromCsv;
import com.senla.fileworker.imports.mergeimport.Merger;
import com.senla.fileworker.imports.mergeimport.MergerBook;
import com.senla.mainmodule.repositories.IRepositoryBook;
import com.senla.mainmodule.repositories.IRepositoryRequest;
import com.senla.mainmodule.services.IServiceBook;
import com.senla.mainmodule.util.dataworker.DataWorker;
import com.senla.mainmodule.util.dataworker.IDataWorker;
import entities.Book;
import entities.Request;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class ServiceBook extends Service implements IServiceBook {

    private static final Logger log = Logger.getLogger(ServiceBook.class);
    private IRepositoryBook repositoryBook;
    private IRepositoryRequest repositoryRequest;
    private IDataWorker fileWorker = new DataWorker();

    public ServiceBook(IRepositoryBook repositoryBook, IRepositoryRequest repositoryRequest) {
        this.repositoryBook = repositoryBook;
        this.repositoryRequest = repositoryRequest;
    }

    @Override
    public void addBook(String name, Date datePublication, Date dateAddedBookToStore, Double price, String description, Boolean isAvailable) {
        Boolean isOld = false;
        Book newBook = new Book(name, datePublication, dateAddedBookToStore, price, description, isAvailable, isOld);
        repositoryBook.add(newBook);
        notifyObservers("Добавлена книга: " + newBook);

        if (ALLOW_MARK_REQUESTS){
            for (Request request : repositoryRequest.getAll()) {
                if (newBook.getNameBook().equals(request.getRequireNameBook())) {
                    request.setRequireIsCompleted(true);
                }
            }
        }
    }

    @Override
    public void deleteBookById(Long id) {
        if (repositoryBook.getById(id) != null) {
            notifyObservers("Удалена книга: " + repositoryBook.getById(id));
            repositoryBook.deleteById(id);
        }else {
            notifyObservers("Книги с таким индексом нет !!!");
        }
    }

    @Override
    public void sortByAlphabet() {
        repositoryBook.getAll().sort(new ComparatorBookByAlphabet());
        repositoryBook.getAll().sort(new ComparatorBookByAlphabet());
        notifyObservers("Книги отсортированы по алфавиту");
    }

    @Override
    public void sortByDatePublication() {
        repositoryBook.getAll().sort(new ComparatorBookByDatePublication());
        notifyObservers("Книги отсортированы по дате публикации");
    }

    @Override
    public void sortByPrice() {
        repositoryBook.getAll().sort(new ComparatorBookByPrice());
        notifyObservers("Книги отсортированы по цене");
    }

    @Override
    public void sortByAvailability() {
        repositoryBook.getAll().sort(new ComparatorBookByAvailability());
        notifyObservers("Книги отсортированы по доступности");
    }

    @Override
    public List<Book> getAll() {
        return repositoryBook.getAll();
    }

    @Override
    public Book getByName(String name) {
        return repositoryBook.getByName(name);
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        sortByDateAddedToShop();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        Date periodSixMonth = cal.getTime();
        List<Book> bookList = new ArrayList<>();
        for (Book book: repositoryBook.getAll()) {
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
        for (Book book : repositoryBook.getAll()) {
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
    if (repositoryBook.getById(id) != null){
        Book book = repositoryBook.getById(id);
            return book.getDescription();
        }
        return null;
    }

    @Override
    public void markBookOld(){
        if (BOOK_IS_OLD != null){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -BOOK_IS_OLD);
            Date markOld = cal.getTime();
            for (Book book : repositoryBook.getAll()) {
                if (book.getDateAddedBookToStore().before(markOld)){
                    book.setOld(true);
                }
            }
        }
    }

    @Override
    public void setLastId() {
        Long id = 0L;
        for (Book book : repositoryBook.getAll()) {
            if (book.getId() > id){
                id = book.getId();
            }
        }
        repositoryBook.setLastId(id);
    }

    private void sortByDateAddedToShop() {
        repositoryBook.getAll().sort(new ComparatorBookByDateAddedToShop());
        notifyObservers("Книги отсортированы по дате добавления в магазин");
    }

    @Override
    public void exportToCsv() {
        super.writeToCsv(repositoryBook.getAll());
    }

    public void importFromCsv(){
        ImportBookFromCsv importList = new ImportBookFromCsv();
        List<Book> temp = importList.importListFromFile(PATH_BOOK_CSV);
        Merger<Book> merger = new MergerBook(repositoryBook.getAll());
        repositoryBook.setAll(merger.merge(temp));
    }

    @Override
    public void readDataFromFile(String path) {
        repositoryBook.getAll().clear();
        repositoryBook.setAll(fileWorker.readDataFromFile(path));
    }

    @Override
    public void writeDataToFile() {
        fileWorker.writeDataToFile(this, repositoryBook.getAll());
    }
}
