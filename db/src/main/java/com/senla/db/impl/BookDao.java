package com.senla.db.impl;

import com.senla.db.IBookDao;
import com.senla.util.date.DateUtil;
import entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDao implements IBookDao {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String ID = "id";
    private static final String PRICE = "price";
    private static final String NAME_BOOK = "nameBook";
    private static final String IS_OLD = "isOld";
    private static final String IS_AVAILABLE = "isAvailable";
    private static final String DESCRIPTION = "description";
    private static final String DATE_OF_PUBLICATION = "dateOfPublication";
    private static final String DATE_ADDED_BOOK_TO_STORE = "dateAddedBookToStore";

    @Override
    public void add(Connection connection, Book book) throws SQLException {
        String sql = "INSERT INTO books (dateAddedBookToStore, dateOfPublication, description, isAvailable, isOld, nameBook, price) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dateFormat.format(book.getDateAddedBookToStore()));
            statement.setString(2, dateFormat.format(book.getDateOfPublication()));
            statement.setString(3, book.getDescription());
            statement.setBoolean(4, book.getAvailable());
            statement.setBoolean(5, book.getOld());
            statement.setString(6, book.getNameBook());
            statement.setDouble(7, book.getPrice());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(Connection connection, Long id) throws SQLException {
        String sql = "DELETE FROM books WHERE id =" + id;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        }
    }

    @Override
    public Book getById(Connection connection, Long id) throws SQLException {
        return getBook(connection, "SELECT * FROM books WHERE id=" + id);
    }

    @Override
    public void update(Connection connection, Book book) throws SQLException {
        String sql = "UPDATE books SET dateAddedBookToStore=?, price=?, nameBook=?, dateOfPublication=?," +
                " description=?, isAvailable=?, isOld=?  WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dateFormat.format(book.getDateAddedBookToStore()));
            statement.setDouble(2, book.getPrice());
            statement.setString(3, book.getNameBook());
            statement.setString(4, dateFormat.format(book.getDateOfPublication()));
            statement.setString(5, book.getDescription());
            statement.setBoolean(6, book.getAvailable());
            statement.setBoolean(7, book.getOld());
            statement.setLong(8, book.getId());
            statement.execute();
        }
    }

    @Override
    public List<Book> getAll(Connection connection) throws SQLException {
        return getBooks(connection, "SELECT * FROM books");
    }

    @Override
    public void addAll(Connection connection, List<Book> books) throws SQLException {
        for (Book book : books) {
            add(connection, book);
        }
    }

    @Override
    public Book getByName(Connection connection, String name) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM books WHERE nameBook=")
                .append("'").append(name).append("'");
        return getBook(connection, String.valueOf(stringBuilder));
    }

    @Override
    public List<Book> getSortedByPrice(Connection connection) throws SQLException {
        return getBooks(connection, "SELECT * FROM books ORDER BY price");
    }

    @Override
    public List<Book> getSortedByAlphabet(Connection connection) throws SQLException {
        return getBooks(connection, "SELECT * FROM books ORDER BY nameBook");
    }

    @Override
    public List<Book> getSortedByDatePublication(Connection connection) throws SQLException {
        return getBooks(connection, "SELECT * FROM books ORDER BY dateOfPublication");
    }

    @Override
    public List<Book> getSortedByAvailability(Connection connection) throws SQLException {
        return getBooks(connection, "SELECT * FROM books ORDER BY isAvailable");
    }

    @Override
    public List<Book> getPeriodMoreSixMonthByDate(Connection connection) throws SQLException {
        String sql = "SELECT * FROM books WHERE dateAddedBookToStore > '" + dateFormat.format(DateUtil.minusMonths(6)) + "' ORDER BY dateAddedBookToStore";
        return getBooks(connection, sql);
    }

    @Override
    public List<Book> getNewBooks(Connection connection, Date periodOfMonth) throws SQLException {
        String sql = "SELECT * FROM books WHERE dateAddedBookToStore > '" + dateFormat.format(periodOfMonth) + "' ORDER BY dateAddedBookToStore";
        return getBooks(connection, sql);
    }

    private List<Book> getBooks(Connection connection, String sql) throws SQLException {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Book book = new Book();
                book.setId(result.getLong(ID));
                book.setDateAddedBookToStore(result.getDate(DATE_ADDED_BOOK_TO_STORE));
                book.setDateOfPublication(result.getDate(DATE_OF_PUBLICATION));
                book.setDescription(result.getString(DESCRIPTION));
                book.setAvailable(result.getBoolean(IS_AVAILABLE));
                book.setOld(result.getBoolean(IS_OLD));
                book.setNameBook(result.getString(NAME_BOOK));
                book.setPrice(result.getDouble(PRICE));
                books.add(book);
            }
        }
        return books;
    }

    private Book getBook(Connection connection, String sql) throws SQLException {
        Book book = new Book();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            if (result.next()) {
                book.setId(result.getLong(ID));
                book.setDateAddedBookToStore(result.getDate(DATE_ADDED_BOOK_TO_STORE));
                book.setDateOfPublication(result.getDate(DATE_OF_PUBLICATION));
                book.setDescription(result.getString(DESCRIPTION));
                book.setAvailable(result.getBoolean(IS_AVAILABLE));
                book.setOld(result.getBoolean(IS_OLD));
                book.setNameBook(result.getString(NAME_BOOK));
                book.setPrice(result.getDouble(PRICE));
                return book;
            }
        }
        return null;
    }
}
