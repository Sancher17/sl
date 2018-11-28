package com.cafe.services;

import com.cafe.api.dao.IOrderDao;
import com.cafe.api.services.IOrderService;
import com.cafe.model.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService implements IOrderService {

    @Autowired
    IOrderDao ordersDao;

    public OrderService() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }

    @Override
    public void add(Order order) {
        Order order1 = new Order();



    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public Order getById(Long id) {
        return ordersDao.getById(id, Order.class);

    }

    @Override
    public List<Order> getAll() {
        return ordersDao.getAll(Order.class);
    }
}
