package com.senla.mainmodule.services;

import com.senla.mainmodule.repositories.IRepositoryOrder;
import entities.Order;

import java.util.Date;
import java.util.List;

public interface IServiceOrder extends IService {

    void addOrder(Order order);

    void addOrder(Long bookId);

    void addOrder(Date startOrder, Long bookId);

    void deleteOrderById(Long id);

    void setCompleteOrderById(Long id);

    void setCompleteOrderById(Long id, Date dateOfCompleted);

    void sortCompletedOrdersByDate();

    void sortOrdersByPrice();

    void sortOrdersByState();

    List<Order> getAll();

    List<Order> getCompletedOrders();

    List<Order> getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate);

    List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate);

    Double getOrdersFullAmountByPeriod(Date startDate, Date endDate);

    Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate);

    Order getOrderById(Long id);

//    IRepositoryOrder getRepositoryOrder();

    Order cloneOrder(Long id);
}
