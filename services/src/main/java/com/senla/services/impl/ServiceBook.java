package com.senla.services.impl;

import com.senla.db.IBookDao;
import com.senla.db.IRequestDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.services.IServiceBook;
import com.senla.util.date.DateUtil;
import entities.Book;
import entities.Request;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class ServiceBook extends Service implements IServiceBook {

    private static final Logger log = Logger.getLogger(ServiceBook.class);

    private static final String BOOK_ADDED = "Добавлена книга: ";
    private static final String BOOK_DELETED = "Удалена книга: ";
    private static final String NO_BOOK_WITH_SUCH_INDEX = "Книги с таким индексом нет !!!";
    private static final String BOOKS_SORTED_BY_ALPHABET = "Книги отсортированы по алфавиту";
    private static final String BOOKS_SORTED_BY_DATE_OF_PUBLICATION = "Книги отсортированы по дате публикации";
    private static final String BOOKS_SORTED_BY_PRICE = "Книги отсортированы по цене";
    private static final String BOOKS_SORTED_BY_AVAILABILITY = "Книги отсортированы по доступности";
    private static final String BOOKS_ADDED_TO_STORE_MORE_SIX_MONTH = "Книги добавленные в магазин более 6 месяцев назад";

    private IBookDao bookDao;
    private IRequestDao requestDao;
    private IFileWorker fileWorker;

    public ServiceBook() {
        this.bookDao = DependencyInjection.getBean(IBookDao.class);
        this.requestDao = DependencyInjection.getBean(IRequestDao.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addBook(Book book) {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(BOOK_ADDED + book.getNameBook());
        try {
            connection.setAutoCommit(false);
            bookDao.add(connection, book);
            if (ALLOW_MARK_REQUESTS) {
                List<Request> requests = requestDao.getAll(connection);
                for (Request request : requests) {
                    if (book.getNameBook().equals(request.getRequireNameBook())) {
                        request.setRequireIsCompleted(true);
                        requestDao.update(connection, request);
                    }
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            String message = CAN_NOT_ADD_DATA_TO_BD;
            log.error(message + e);
            notifyObservers(message);
            try {
                connection.setAutoCommit(true);
                connection.rollback();
            } catch (SQLException e1) {
                log.error(CAN_NOT_DO_ROLLBACK + e1);
            }
        }
    }

    @Override
    public void deleteBookById(Long id) {
        Connection connection = ConnectionDB.getConnection();
        try {
            Book book = bookDao.getById(connection, id);
            if (book != null) {
                notifyObservers(BOOK_DELETED + book);
                bookDao.deleteById(connection, id);
            } else {
                notifyObservers(NO_BOOK_WITH_SUCH_INDEX);
            }
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
    }

    @Override
    public List<Book> getBooksSortedByAlphabet() {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(BOOKS_SORTED_BY_ALPHABET);
        try {
            return bookDao.getSortedByAlphabet(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByDatePublication() {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(BOOKS_SORTED_BY_DATE_OF_PUBLICATION);
        try {
            return bookDao.getSortedByDatePublication(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByPrice() {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(BOOKS_SORTED_BY_PRICE);
        try {
            return bookDao.getSortedByPrice(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByAvailability() {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(BOOKS_SORTED_BY_AVAILABILITY);
        try {
            return bookDao.getSortedByAvailability(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;

    }

    @Override
    public List<Book> getAll() {
        Connection connection = ConnectionDB.getConnection();
        try {
            return bookDao.getAll(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(BOOKS_ADDED_TO_STORE_MORE_SIX_MONTH);
        try {
            return bookDao.getPeriodMoreSixMonthByDate(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByPrice() {
        Connection connection = ConnectionDB.getConnection();
        try {
            return bookDao.getPeriodMoreSixMonthByDate(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public Book getBookById(Long id) {
        Connection connection = ConnectionDB.getConnection();
        try {
            return bookDao.getById(connection, id);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public String getBookDescriptionById(Long id) {
        Connection connection = ConnectionDB.getConnection();
        try {
            Book book = bookDao.getById(connection, id);
            if (book != null) {
                return book.getDescription();
            }
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public void markBookOld() {
        Connection connection = ConnectionDB.getConnection();
        if (BOOK_IS_OLD != null) {
            Date periodOfMonth = DateUtil.minusMonths(BOOK_IS_OLD);
            try {
                List<Book> books = bookDao.getNewBooks(connection, periodOfMonth);
                for (Book book : books) {
                    book.setOld(true);
                    bookDao.update(connection, book);
                }
            } catch (SQLException e) {
                String message = NO_DATA_FROM_BD;
                log.error(message + e);
                notifyObservers(message);
            }
        }
    }

    @Override
    public void exportToCsv() {
        Connection connection = ConnectionDB.getConnection();
        try {
            super.writeToCsv(bookDao.getAll(connection));
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
    }

    @Override
    public void importFromCsv() {
        List<Book> importListFromFile = fileWorker.importListFromFile(PATH_BOOK_CSV, Book.class);
        notifyObservers(PATH_BOOK_CSV);
        merge(importListFromFile, bookDao);
    }
}
