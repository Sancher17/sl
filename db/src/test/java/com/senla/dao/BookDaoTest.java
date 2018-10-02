package com.senla.dao;

import com.senla.db.BookDao;
import com.senla.db.HelperJDBC;
import com.senla.db.IBookDao;
import entities.Book;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private Connection connection;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    void setUp() {
        this.connection = HelperJDBC.getConnection();
    }

    @Test
    public void testGetAll() throws Exception {

        IBookDao bookDao = new BookDao();
        List<Book> list = bookDao.getAll();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

}