package com.senla.hibernate.impl;

import com.senla.hibernate.IOrderDao;
import entities.Order;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class OrderDao implements IOrderDao {


    @Override
    public List<Order> getCompletedSortedByDate(Session session) {
        return null;
    }

    @Override
    public List<Order> getSortedByPrice(Session session) {
        return null;
    }

    @Override
    public List<Order> getSortedByState(Session session) {
        return null;
    }

    @Override
    public List<Order> getCompleted(Session session) {
        return null;
    }

    @Override
    public List<Order> getCompletedSortedByDateOfPeriod(Session session, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Order> getCompletedSortedByPriceOfPeriod(Session session, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Double getFullAmountByPeriod(Session session, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Integer getQuantityCompletedByPeriod(Session session, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public void copyOrder(Session session, Long id) {

    }

    @Override
    public void add(Session session, Order obj) {

    }

    @Override
    public void deleteById(Session session, Long id) {

    }

    @Override
    public Order getById(Session session, Long id) {
        return null;
    }

    @Override
    public void update(Session session, Order order) {

    }

    @Override
    public List<Order> getAll(Session session) {
        return null;
    }

    @Override
    public void addAll(Session session, List<Order> notExistList) {

    }
}