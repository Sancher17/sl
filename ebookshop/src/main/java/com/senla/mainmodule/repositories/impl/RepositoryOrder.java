package com.senla.mainmodule.repositories.impl;


import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.repositories.IRepositoryOrder;
import com.senla.mainmodule.repositories.util.Id;

import java.util.ArrayList;
import java.util.List;

public class RepositoryOrder implements IRepositoryOrder {

    private static Long lastId = 0L;
    private List<Order> orders = new ArrayList<>();
    private static RepositoryOrder instance = null;

    public static RepositoryOrder getInstance() {
        if (instance == null) {
            instance = new RepositoryOrder();
        }
        return instance;
    }

    private RepositoryOrder() {
    }

    @Override
    public void add(Order order) {
        lastId = Id.nextId(lastId);
        order.setId(lastId);
        orders.add(order);
    }

    @Override
    public void deleteById(Long id) {
        orders.removeIf(order -> order.getId().equals(id));
    }

    @Override
    public Order getById(Long id) {
        for(Order order: orders){
            if(order.getId().equals(id)){
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    @Override
    public Long getLastId() {
        return lastId;
    }

    @Override
    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }
}
