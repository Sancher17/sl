package com.senla.services;

import entities.Order;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IServiceOrder extends IService<Order> {

    void addOrder(Order order);

    void deleteOrderById(Long id);

    void setCompleteOrderById(Long id);

    List<Order> getCompletedOrdersSortedByDate();

    List<Order> getOrdersSortedByPrice();

    List<Order> getOrdersSortedByState();

    List<Order> getAll();

    List<Order> getCompletedOrders();

    List<Order> getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate);

    List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate);

    Double getFullAmountOfOrdersByPeriod(Date startDate, Date endDate);

    Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate);

    Order getOrderById(Long id);

}
