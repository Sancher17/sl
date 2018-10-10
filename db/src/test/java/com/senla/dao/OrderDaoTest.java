package com.senla.dao;

import com.senla.db.IBookDao;
import com.senla.db.IOrderDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.db.impl.BookDao;
import com.senla.db.impl.OrderDao;
import com.senla.di.DependencyInjection;
import entities.Book;
import entities.Order;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    private IOrderDao orderDao = new OrderDao();
    private IBookDao bookDao = new BookDao();
    private Order order = new Order();
    private Connection connection = ConnectionDB.getConnection();

    void init(){
        order.setDateOfStartedOrder(new Date());
        order.setDateOfCompletedOrder(new Date());
        order.setCompletedOrder(true);
        try {
            order.setBook(bookDao.getById(connection, 5L));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add()  throws SQLException{
        init();
        orderDao.add(connection, order);
    }

    @Test
    public void deleteById()  throws SQLException{
        Long id = 7L;
        orderDao.deleteById(connection, id);
        assertNull(orderDao.getById(connection, id));
    }

    @Test
    public void getById()  throws SQLException {
        Order order = orderDao.getById(connection, 5L);
        assertEquals(5, (long) order.getId());
    }

    @Test
    public void update() {
        fail();
    }

    @Test
    public void getAll()  throws SQLException{
        List<Order> list = orderDao.getAll(connection);
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void addAll() {
        fail();
    }
}