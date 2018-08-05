package repositories.impl;

import entities.Order;
import repositories.IRepositoryOrder;
import util.ID;

import java.util.ArrayList;
import java.util.List;

public class RepositoryOrder implements IRepositoryOrder {

    private Long lastId = 0L;
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
        lastId = ID.nextId(lastId);
        order.setId(lastId);
        orders.add(order);
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
    public void deleteById(Long id) {
        orders.removeIf(order -> order.getId().equals(id));
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }
}
