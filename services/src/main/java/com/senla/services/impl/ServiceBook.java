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
        notifyObservers("Добавлена книга: " + book.getNameBook());
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
        } catch (SQLException e) {
            log.error("Не удачная попытка добавить Book в БД - " + e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                log.error("Не удачная попытка сделать rollback - " + e);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.error("Не удачная попытка вернуть setAutoCommit(true) - " + e);
            }
        }
    }

    @Override
    public void deleteBookById(Long id) {
        try {
            Book book = bookDao.getById(connection, id);
            if (book != null) {
                notifyObservers("Удалена книга: " + book);
                bookDao.deleteById(connection, id);
            } else {
                notifyObservers("Книги с таким индексом нет !!!");
            }
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
    }

    @Override
    public List<Book> getBooksSortedByAlphabet() {
        notifyObservers("Книги отсортированы по алфавиту");
        try {
            return bookDao.getSortedByAlphabet(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByDatePublication() {
        notifyObservers("Книги отсортированы по дате публикации\n");
        try {
            return bookDao.getSortedByDatePublication(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByPrice() {
        notifyObservers("Книги отсортированы по цене");
        try {
            return bookDao.getSortedByPrice(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByAvailability() {
        notifyObservers("Книги отсортированы по доступности");
        try {
            return bookDao.getSortedByAvailability(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;

    }

    @Override
    public List<Book> getAll() {
        try {
            return bookDao.getAll(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        notifyObservers("Книги добавленные в магазин более 6 месяцев назад\n");
        try {
            return bookDao.getPeriodMoreSixMonthByDate(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByPrice() {
        try {
            return bookDao.getPeriodMoreSixMonthByDate(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public Book getBookById(Long id) {
        try {
            return bookDao.getById(connection, id);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public String getBookDescriptionById(Long id) {
        try {
            Book book = bookDao.getById(connection, id);
            if (book != null) {
                return book.getDescription();
            }
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public void markBookOld() {
        if (BOOK_IS_OLD != null) {
            Date periodOfMonth = DateUtil.minusMonths(BOOK_IS_OLD);
            try {
                List<Book> books = bookDao.getNewBooks(connection, periodOfMonth);
                for (Book book : books) {
                    book.setOld(true);
                    bookDao.update(connection, book);
                }
            } catch (SQLException e) {
                log.error("Не удалось получить данные с БД " + e);
                notifyObservers("Не удалось получить данные с БД");
            }
        }
    }

    @Override
    public void exportToCsv() {
        try {
            super.writeToCsv(bookDao.getAll(connection));
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
    }

    @Override
    public void importFromCsv() {
        List<Book> importListFromFile = fileWorker.importListFromFile(PATH_BOOK_CSV, Book.class);
        notifyObservers(PATH_BOOK_CSV);
        merge(importListFromFile, bookDao);
    }
}
