package com.cafe.dao;

import com.cafe.api.dao.IOrderDao;
import com.cafe.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    public OrderDao() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }


}
