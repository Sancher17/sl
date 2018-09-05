package com.senla.repositories;

import entities.Order;

import java.util.List;

public interface IRepositoryOrder extends IRepository<Order>{

    void setAll(List<Order> entity);

    Long findMaxId();
}
