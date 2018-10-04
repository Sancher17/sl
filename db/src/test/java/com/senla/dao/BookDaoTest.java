package com.senla.dao;

import com.senla.db.IBookDao;
import com.senla.db.impl.BookDao;
import com.senla.di.DependencyInjection;
import entities.Book;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private IBookDao bookDao = DependencyInjection.getBean(IBookDao.class);
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
    public void add() {
        init();
        bookDao.add(book);
        Book testBook = bookDao.getByName(name);
        book.setId(testBook.getId());
        currentId = testBook.getId();
        assertEquals(bookDao.getByName(book.getNameBook()), book);
    }

    @Test
    public void deleteById() {
        Book book = bookDao.getByName("Test");
        bookDao.deleteById(book.getId());
        assertNull(bookDao.getById(book.getId()));
    }

    @Test
    public void getById() {
        Book book = bookDao.getById(5L);
        assertEquals(5, (long) book.getId());
    }

    @Test
    public void update() {
    }

    @Test
    public void getAll() {
        List<Book> list = bookDao.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void addAll() {
    }

    @Test
    public void getByName() {
//        /        assertEquals(bookDao.getByName(name), book);
    }
}