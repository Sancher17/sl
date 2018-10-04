package com.senla.db.impl;


import com.senla.db.connection.ConnectionDB;
import com.senla.db.IBookDao;
import entities.Book;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {

    private static final Logger log = Logger.getLogger(BookDao.class);
    private Connection connection;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BookDao() {
        this.connection = ConnectionDB.getConnection();
    }

    @Override
    public void add(Book book) {
        String sql = "INSERT INTO books (dateAddedBookToStore, dateOfPublication, description, isAvailable, isOld, nameBook, price) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, sdf.format(book.getDateAddedBookToStore()));
            statement.setString(2, sdf.format(book.getDateOfPublication()));
            statement.setString(3, book.getDescription());
            statement.setBoolean(4, book.getAvailable());
            statement.setBoolean(5, book.getOld());
            statement.setString(6, book.getNameBook());
            statement.setDouble(7, book.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
           log.error("Не удачная попытка добавить Book в БД - " + e);
        }
    }


    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id =" + id;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Не удачная попытка удалить Book из БД - " + e);
        }

    }

    @Override
    public Book getById(Long id) {
        String sql = "SELECT * FROM books WHERE id="+id;
        Book book = new Book();
        try(PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()) {
            if(result.next()){
                book.setId(result.getLong("id"));
                book.setDateAddedBookToStore(result.getDate("dateAddedBookToStore"));
                book.setDateOfPublication(result.getDate("dateOfPublication"));
                book.setDescription(result.getString("description"));
                book.setAvailable(result.getBoolean("isAvailable"));
                book.setOld(result.getBoolean("isOld"));
                book.setNameBook(result.getString("nameBook"));
                book.setPrice(result.getDouble("price"));
                return book;
            }
        } catch (SQLException e) {
            log.error("Не удачная попытка получить Book из БД - " + e);
        }
        return null;
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try(PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()) {
            result.next();
            while (result.next()){
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
        } catch (SQLException e) {
            log.error("Не удачная попытка получить список Book из БД - " + e);
        }
        return books;
    }

    @Override
    public void addAll(List<Book> notExistList) {

    }



    @Override
    public Book getByName(String name) {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM books WHERE nameBook=")
                .append("'").append(name).append("'");
        Book book = new Book();
        try(PreparedStatement statement = connection.prepareStatement(String.valueOf(stringBuilder));
            ResultSet result = statement.executeQuery()) {

            result.next();
            book.setId(result.getLong("id"));
            book.setDateAddedBookToStore(result.getDate("dateAddedBookToStore"));
            book.setDateOfPublication(result.getDate("dateOfPublication"));
            book.setDescription(result.getString("description"));
            book.setAvailable(result.getBoolean("isAvailable"));
            book.setOld(result.getBoolean("isOld"));
            book.setNameBook(result.getString("nameBook"));
            book.setPrice(result.getDouble("price"));
        } catch (SQLException e) {
            log.error("Не удачная попытка получить Book из БД - " + e);
        }
        return book;
    }

    enum SqlBook {
//        INSERT("INSERT INTO books (dateAddedBookToStore, dateOfPublication, description, isAvailable, isOld, nameBook, price) VALUES (?,?,?,?,?,?,?)"),
//        DELETE_BY_ID("DELETE FROM books WHERE id ="),
//
    }

}
