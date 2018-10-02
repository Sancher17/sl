package com.senla.db;


import entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.senla.db.BookDao.SQLUser.GET_ALL;
import static com.senla.db.BookDao.SQLUser.INSERT;

public class BookDao implements IBookDao {


    private Connection connection;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BookDao() {
        this.connection = HelperJDBC.getConnection();
    }

    @Override
    public void add(Book book) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT.QUERY)){
            statement.setString(1, sdf.format(book.getDateAddedBookToStore()));
            statement.setString(2, sdf.format(book.getDateOfPublication()));
            statement.setString(3, book.getDescription());
            statement.setBoolean(4, book.getAvailable());
            statement.setBoolean(5, book.getOld());
            statement.setString(6, book.getNameBook());
            statement.setDouble(7, book.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Book getById(Long id) {
        String sql = "SELECT * FROM books WHERE id="+id;
        Book book = new Book();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
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
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(GET_ALL.QUERY)) {
            ResultSet result = statement.executeQuery();
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
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void addAll(List<Book> notExistList) {

    }


    @Override
    public void setAll(List<Book> entity) {

    }

    @Override
    public Book getByName(String name) {
        return null;
    }

    enum SQLUser {
        GET("SELECT u.id, u.login, u.password, r.id AS rol_id, r.role FROM users AS u LEFT JOIN roles AS r ON u.role = r.id WHERE u.login = (?)"),
        GET_ALL("SELECT * FROM books"),
        INSERT("INSERT INTO books (dateAddedBookToStore, dateOfPublication, description, isAvailable, isOld, nameBook, price) VALUES (?,?,?,?,?,?,?)"),
        DELETE("DELETE FROM users WHERE id = (?) AND login = (?) AND password = (?) RETURNING id"),
        UPDATE("UPDATE users SET password = (?) WHERE id = (?) RETURNING id");


        String QUERY;

        SQLUser(String QUERY) {
            this.QUERY = QUERY;
        }
    }

}
