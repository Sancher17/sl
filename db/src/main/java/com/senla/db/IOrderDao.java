package com.senla.db;

import entities.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public interface IOrderDao extends GenericDAO<Order> {

    List<Order> getCompletedSortedByDate(Connection connection) throws SQLException;

    List<Order> getSortedByPrice(Connection connection) throws SQLException;

    List<Order> getSortedByState(Connection connection) throws SQLException;

    List<Order> getCompleted(Connection connection) throws SQLException;

    List<Order> getCompletedSortedByDateOfPeriod(Connection connection, Date startDate, Date endDate) throws SQLException;

    List<Order> getCompletedSortedByPriceOfPeriod(Connection connection, Date startDate, Date endDate) throws SQLException;

    Double getFullAmountByPeriod(Connection connection, Date startDate, Date endDate) throws SQLException;

    Integer getQuantityCompletedByPeriod(Connection connection, Date startDate, Date endDate) throws SQLException;
}
