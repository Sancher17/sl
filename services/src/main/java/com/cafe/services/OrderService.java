package com.cafe.services;

import com.cafe.api.dao.IOrderDao;
import com.cafe.api.services.IOrderService;
import com.cafe.model.Goods;
import com.cafe.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderDao orderDao;


    @Override
    public void add(Order order) {
        // сумма цен товаров из ордера
//        if (order.getAmount() != null) {
//            Double amount = order.getGoods().stream().mapToDouble(Goods::getSellPrice).sum();
//            order.setAmount(amount);
//        }
        orderDao.add(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public Order getById(Long id) {
        return orderDao.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order getByName(String name) {
        // no such field "name"
        return null;
    }

    // TODO: 04.12.2018 разобраться с интерфейсами
    @Override
    public List<Order> getListByPeriod(LocalDateTime start, LocalDateTime end) {
        return orderDao.getListByPeriod(start, end);
    }
}
