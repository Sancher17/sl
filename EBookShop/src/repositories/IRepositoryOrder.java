package repositories;

import entities.Order;

import java.util.List;

public interface IRepositoryOrder {

    void add(Order order);

    Order getById(Long id);

    void deleteById(Long id);

    List<Order> getOrders();

    void setOrders(List<Order> orders);

    void setLastId(Long lastId);
}
