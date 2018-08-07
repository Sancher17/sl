package com.senla.mainmodule.repositories;

import com.senla.mainmodule.entities.Order;

import java.util.List;

public interface IRepositoryOrder {

    void add(Order order);

    void deleteById(Long id);

    Order getById(Long id);

    List<Order> getOrders();

    Long getLastId();

    void setOrders(List<Order> orders);

    void setLastId(Long lastId);
}
