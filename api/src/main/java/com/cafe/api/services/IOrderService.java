package com.cafe.api.services;

import com.cafe.model.Order;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IOrderService extends IService<Order> {

    List<Order> getListByPeriod(LocalDateTime start, LocalDateTime end);

    List<Order> getListGoodsById(Long id);

    List<Order> getListOrdersForUser(Long id);
}
