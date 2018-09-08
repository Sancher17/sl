package com.senla.repositories.impl;

import com.senla.repositories.IRepositoryOrder;
import com.senla.repositories.util.Id;
import entities.Book;
import entities.Order;

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
        lastId = findMaxId();
        lastId = Id.nextId(lastId);
        if (order.getId() == null){
            order.setId(lastId);
        }else if (order.getId() < lastId){
            order.setId(lastId);
        }
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
    public void update(Order entity) {
        int index;
        Long id = entity.getId();
        for (Order newBook : orders) {
            if (newBook.getId().equals(id)) {
                index = orders.indexOf(newBook);
                orders.set(index, newBook);
            }
        }

    }


    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public void addAll(List<Order> notExistList) {
        orders.addAll(notExistList);
    }

    @Override
    public void setAll(List orders) {
        this.orders = orders;
    }

    @Override
    public Long findMaxId(){
        Long maxId = 0L;
        for (Order order:  orders) {
            if (order.getId() > maxId) {
                maxId = order.getId();
            }
        }
        return maxId;
    }
}
