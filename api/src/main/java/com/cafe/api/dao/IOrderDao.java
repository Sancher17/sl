package com.cafe.api.dao;

import com.cafe.model.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IOrderDao extends IGenericDao<Order> {

    List<Order> getListByPeriod(LocalDateTime start, LocalDateTime end);
}
