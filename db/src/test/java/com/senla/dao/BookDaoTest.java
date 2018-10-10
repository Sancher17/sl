package com.senla.dao;

import com.senla.db.IBookDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.db.impl.BookDao;
import com.senla.di.DependencyInjection;
import entities.Book;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private Connection connection = ConnectionDB.getConnection();
    private IBookDao bookDao = new BookDao();
    private Book book = new Book();
    private Long currentId;
    private String name = "Test";
    private Double price = 12345.0;
    private Boolean isOld = false;
    private Boolean isAvailable = true;
    private String description = "Book for test";

    void init(){
        book.setNameBook(name);
        book.setPrice(price);
        book.setOld(isOld);
        book.setAvailable(isAvailable);
        book.setDescription(description);
        book.setDateOfPublication(new Date());
        book.setDateAddedBookToStore(new Date());
    }

    @Test
    public void add() throws SQLException {
        init();
        bookDao.add(connection, book);
        Book testBook = bookDao.getByName(connection, name);
        book.setId(testBook.getId());
        currentId = testBook.getId();
        assertEquals(bookDao.getByName(connection, book.getNameBook()), book);
    }

    @Test
    public void deleteById() throws SQLException{
        Book book = bookDao.getByName(connection, "Test");
        bookDao.deleteById(connection, book.getId());
        assertNull(bookDao.getById(connection, book.getId()));
    }

    @Test
    public void getById()  throws SQLException{
        Book book = bookDao.getById(connection, 5L);
        assertEquals(5, (long) book.getId());
    }

    @Test
    public void update() {
    }

    @Test
    public void getAll() throws SQLException {
        List<Book> list = bookDao.getAll(connection);
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void addAll() {
    }

    @Test
    public void getByName() {
//        assertEquals(bookDao.getByName(name), book);
    }
}