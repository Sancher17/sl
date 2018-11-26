package com.cafe.dao;

import com.cafe.api.dao.IOrdersDao;
import com.cafe.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends AbstractDao<Order> implements IOrdersDao {

    public OrderDao() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }
}
