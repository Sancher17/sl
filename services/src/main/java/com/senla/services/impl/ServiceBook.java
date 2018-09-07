package com.senla.services.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.imports.IImportFromCsv;
import com.senla.fileworker.imports.mergeimport.Merger;
import com.senla.fileworker.imports.mergeimport.MergerBook;
import com.senla.repositories.IRepositoryBook;
import com.senla.repositories.IRepositoryRequest;
import com.senla.services.IServiceBook;
import com.senla.util.comparators.book.*;
import com.senla.util.dataworker.IDataWorker;
import com.senla.util.date.DateUtil;
import entities.Book;
import entities.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;


public class ServiceBook extends Service implements IServiceBook {

    private IRepositoryBook repositoryBook;
    private IRepositoryRequest repositoryRequest;
    private IDataWorker dataWorker;
//    private IImportBookFromCsv importList;
    private IImportFromCsv importList;

    public ServiceBook(IRepositoryBook repositoryBook, IRepositoryRequest repositoryRequest) {
        this.repositoryBook = repositoryBook;
        this.repositoryRequest = repositoryRequest;
        this.dataWorker = DependencyInjection.getBean(IDataWorker.class);
    }

    @Override
    public void addBook(Book book) {
        notifyObservers("Добавлена книга: " + book);
        repositoryBook.add(book);
        if (ALLOW_MARK_REQUESTS) {
            for (Request request : repositoryRequest.getAll()) {
                if (book.getNameBook().equals(request.getRequireNameBook())) {
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
        } else {
            notifyObservers("Книги с таким индексом нет !!!");
        }
    }

    @Override
    public List<Book> sortByAlphabet() {
        notifyObservers("Книги отсортированы по алфавиту");
        List<Book> sortedList = repositoryBook.getAll();
        sortedList.sort(new ComparatorBookByAlphabet());
        return sortedList;
    }

    @Override
    public List<Book> sortByDatePublication() {
        notifyObservers("Книги отсортированы по дате публикации");
        List<Book> sortedList = repositoryBook.getAll();
        sortedList.sort(new ComparatorBookByDatePublication());
        return sortedList;
    }

    @Override
    public List<Book> sortByPrice() {
        notifyObservers("Книги отсортированы по цене");
        List<Book> sortedList = repositoryBook.getAll();
        sortedList.sort(new ComparatorBookByPrice());
        return sortedList;
    }

    @Override
    public List<Book> sortByAvailability() {
        notifyObservers("Книги отсортированы по доступности");
        List<Book> sortedList = repositoryBook.getAll();
        sortedList.sort(new ComparatorBookByAvailability());
        return sortedList;
    }

    @Override
    public List<Book> getAll() {
        return repositoryBook.getAll();
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        Date periodOfMonth = DateUtil.minusMonths(6);
        List<Book> newList = new ArrayList<>();
        List<Book> existList = sortByDateAddedToShop();
        for (Book book : existList) {
            if (book != null) {
                if (book.getDateAddedBookToStore().before(periodOfMonth)) {
                    newList.add(book);
                }
            }
        }
        return newList;
    }

    private List<Book> sortByDateAddedToShop() {
        notifyObservers("Книги отсортированы по дате добавления в магазин");
        List<Book> sortedList = repositoryBook.getAll();
        sortedList.sort(new ComparatorBookByDateAddedToShop());
        return sortedList;
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByPrice() {
        Date periodOfMonth = DateUtil.minusMonths(6);
        List<Book> newList = new ArrayList<>();
        List<Book> existList = sortByPrice();
        for (Book book : existList) {
            if (book != null) {
                if (book.getDateAddedBookToStore().before(periodOfMonth)) {
                    newList.add(book);
                }
            }
        }
        return newList;
    }

    @Override
    public Book getBookById(Long id) {
        return repositoryBook.getById(id);
    }

    @Override
    public String getBookDescriptionById(Long id) {
        if (repositoryBook.getById(id) != null) {
            Book book = repositoryBook.getById(id);
            return book.getDescription();
        }
        return null;
    }

    @Override
    public void markBookOld() {
        if (BOOK_IS_OLD != null) {
            Date periodOfMonth = DateUtil.minusMonths(BOOK_IS_OLD);
            for (Book book : repositoryBook.getAll()) {
                if (book.getDateAddedBookToStore().before(periodOfMonth)) {
                    book.setOld(true);
                }
            }
        }
    }

    @Override
    public void exportToCsv() {
        super.writeToCsv(repositoryBook.getAll());
    }

    @Override
    public void importFromCsv() {
        importList = DependencyInjection.getBean(IImportFromCsv.class);
        List<Book> temp = importList.importListFromFile(PATH_BOOK_CSV, Book.class);
        Merger<Book> merger = new MergerBook(repositoryBook.getAll());
        repositoryBook.setAll(merger.merge(temp));
    }

    @Override
    public void readDataFromFile(String path) {
        repositoryBook.getAll().clear();
        repositoryBook.setAll(dataWorker.readDataFromFile(path));
    }

    @Override
    public void writeDataToFile() {
        dataWorker.writeDataToFile(this, repositoryBook.getAll());
    }
}
