package com.senla.dao;

import com.senla.db.IRequestDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.db.impl.RequestDao;
import entities.Request;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class RequestDaoTest {

    private Connection connection = ConnectionDB.getConnection();
    private IRequestDao requestDao = new RequestDao();

    @Test
    public void add()  throws SQLException {
        Request request = new Request();
        request.setRequireNameBook("Test");
        request.setRequireIsCompleted(true);
        request.setRequireQuantity(2);
        requestDao.add(connection, request);
    }

    @Test
    public void deleteById()  throws SQLException{
        requestDao.deleteById(connection, 4L);
        assertNull(requestDao.getById(connection, 4L));
    }

    @Test
    public void getById()  throws SQLException{
        Request request = requestDao.getById(connection, 2L);
        assertEquals(2, (long) request.getId());
    }

    @Test
    public void getAll() throws SQLException {
        List<Request> list = requestDao.getAll(connection);
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }
}