package com.senla.hibernate;

import entities.Order;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public interface IOrderDao extends GenericDAO<Order> {

    List<Order> getCompletedSortedByDate(Session session);

    List<Order> getSortedByPrice(Session session);

    List<Order> getSortedByState(Session session);

    List<Order> getCompleted(Session session);

    List<Order> getCompletedSortedByDateOfPeriod(Session session, Date startDate, Date endDate);

    List<Order> getCompletedSortedByPriceOfPeriod(Session session, Date startDate, Date endDate);

    Double getFullAmountByPeriod(Session session, Date startDate, Date endDate);

    Integer getQuantityCompletedByPeriod(Session session, Date startDate, Date endDate);

    void copyOrder(Session session, Long id);
}
