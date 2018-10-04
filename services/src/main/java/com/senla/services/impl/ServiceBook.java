package com.senla.services.impl;

import com.senla.db.IBookDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.repositories.IRepositoryRequest;
import com.senla.services.IServiceBook;
import com.senla.util.date.DateUtil;
import entities.Book;
import entities.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.ALLOW_MARK_REQUESTS;
import static com.senla.mainmodule.constants.Constants.BOOK_IS_OLD;

public class ServiceBook extends Service implements IServiceBook {

    private IBookDao bookDao;
    private IRepositoryRequest repositoryRequest;
    private IFileWorker fileWorker;

    private Connection connection = ConnectionDB.getConnection();

    public ServiceBook() {
        this.bookDao = DependencyInjection.getBean(IBookDao.class);
        this.repositoryRequest = DependencyInjection.getBean(IRepositoryRequest.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addBook(Book book) {
        notifyObservers("Добавлена книга: " + book.getNameBook());
        bookDao.add(book);
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
        if (bookDao.getById(id) != null) {
            notifyObservers("Удалена книга: " + bookDao.getById(id));
            bookDao.deleteById(id);
        } else {
            notifyObservers("Книги с таким индексом нет !!!");
        }
    }

    @Override
    public List<Book> sortByAlphabet() {
        notifyObservers("Книги отсортированы по алфавиту");
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY nameBook";
        try(PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()) {
            result.next();
            createBookFromResultSet(books, result);
        } catch (SQLException e) {

        }
        return books;
    }

    @Override
    public List<Book> sortByDatePublication() {
        notifyObservers("Книги отсортированы по дате публикации\n");
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY dateOfPublication";
        try(PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()) {
            result.next();
            createBookFromResultSet(books, result);
        } catch (SQLException e) {

        }
        return books;
    }

    @Override
    public List<Book> sortByPrice() {
        notifyObservers("Книги отсортированы по цене");
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY price";
        try(PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()) {
            result.next();
            createBookFromResultSet(books, result);
        } catch (SQLException e) {

        }
        return books;
    }

    @Override
    public List<Book> sortByAvailability() {
        notifyObservers("Книги отсортированы по доступности");
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY isAvailable";
        try(PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()) {
            result.next();
            createBookFromResultSet(books, result);
        } catch (SQLException e) {

        }
        return books;
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
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

    private void createBookFromResultSet(List<Book> books, ResultSet result) throws SQLException {
        while (result.next()) {
            Book book = new Book();
            book.setId(result.getLong("id"));
            book.setDateAddedBookToStore(result.getDate("dateAddedBookToStore"));
            book.setDateOfPublication(result.getDate("dateOfPublication"));
            book.setDescription(result.getString("description"));
            book.setAvailable(result.getBoolean("isAvailable"));
            book.setOld(result.getBoolean("isOld"));
            book.setNameBook(result.getString("nameBook"));
            book.setPrice(result.getDouble("price"));
            books.add(book);
        }
    }

    private List<Book> sortByDateAddedToShop() {
        notifyObservers("Книги отсортированы по дате добавления в магазин");
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY dateAddedBookToStore";
        try(PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()) {
            result.next();
            createBookFromResultSet(books, result);
        } catch (SQLException e) {

        }
        return books;
    }

    // TODO: 04.10.2018
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
        return bookDao.getById(id);
    }

    @Override
    public String getBookDescriptionById(Long id) {
        if (bookDao.getById(id) != null) {
            Book book = bookDao.getById(id);
            return book.getDescription();
        }
        return null;
    }

    @Override
    public void markBookOld() {
        if (BOOK_IS_OLD != null) {
            Date periodOfMonth = DateUtil.minusMonths(BOOK_IS_OLD);
            for (Book book : bookDao.getAll()) {
                if (book.getDateAddedBookToStore().before(periodOfMonth)) {
                    book.setOld(true);
                }
            }
        }
    }

    @Override
    public void exportToCsv() {
        super.writeToCsv(bookDao.getAll());
    }

    @Override
    public void importFromCsv() {
//        List<Book> importListFromFile = fileWorker.importListFromFile(PATH_BOOK_CSV, Book.class);
//        notifyObservers(PATH_BOOK_CSV);
//        merge(importListFromFile, bookDao);
    }

}
