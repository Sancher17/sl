package com.senla.dao;

import com.senla.db.IRequestDao;
import com.senla.db.impl.RequestDao;
import entities.Request;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RequestDaoTest {

    private IRequestDao requestDao = new RequestDao();

    @Test
    public void add() {
        Request request = new Request();
        request.setRequireNameBook("Test");
        request.setRequireIsCompleted(true);
        request.setRequireQuantity(2);
        requestDao.add(request);
    }

    @Test
    public void deleteById() {
        requestDao.deleteById(4L);
        assertNull(requestDao.getById(4L));
    }

    @Test
    public void getById() {
        Request request = requestDao.getById(2L);
        assertEquals(2, (long) request.getId());
    }

    @Test
    public void getAll() {
        List<Request> list = requestDao.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }
}