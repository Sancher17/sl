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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    private IOrderDao orderDao = new OrderDao();
    private IBookDao bookDao = new BookDao();
    private Order order = new Order();

    void init(){
        order.setDateOfStartedOrder(new Date());
        order.setDateOfCompletedOrder(new Date());
        order.setCompletedOrder(true);
        order.setBook(bookDao.getById(5L));
    }

    @Test
    public void add() {
        init();
        orderDao.add(order);
    }

    @Test
    public void deleteById() {
        Long id = 7L;
        orderDao.deleteById(id);
        assertNull(orderDao.getById(id));
    }

    @Test
    public void getById() {
        Order order = orderDao.getById(5L);
        assertEquals(5, (long) order.getId());
    }

    @Test
    public void update() {
        fail();
    }

    @Test
    public void getAll() {
        List<Order> list = orderDao.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void addAll() {
        fail();
    }
}