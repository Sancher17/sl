package com.senla.hibernate.impl;

import com.senla.hibernate.IRequestDao;
import entities.Request;
import org.hibernate.Session;

import java.util.List;

public class RequestDao implements IRequestDao {

    @Override
    public List<Request> getSortedByQuantity(Session session) {
        return null;
    }

    @Override
    public List<Request> getSortedByAlphabet(Session session) {
        return null;
    }

    @Override
    public List<Request> getCompleted(Session session) {
        return null;
    }

    @Override
    public List<Request> getNotCompleted(Session session) {
        return null;
    }

    @Override
    public void add(Session session, Request obj) {

    }

    @Override
    public void deleteById(Session session, Long id) {

    }

    @Override
    public Request getById(Session session, Long id) {
        return null;
    }

    @Override
    public void update(Session session, Request request) {

    }

    @Override
    public List<Request> getAll(Session session) {
        return null;
    }

    @Override
    public void addAll(Session session, List<Request> notExistList) {

    }
}