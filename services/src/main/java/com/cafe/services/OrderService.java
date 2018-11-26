package com.cafe.services;

import com.cafe.api.dao.IOrdersDao;
import com.cafe.api.services.IOrderService;
import com.cafe.model.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService implements IOrderService {

    @Autowired
    IOrdersDao ordersDao;

    public OrderService() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }

    @Override
    public void add(Order order) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public Order getById(Long id) {
        return null;

    }

    @Override
    public List<Order> getAll() {
        return ordersDao.getAll(Order.class);
    }
}
