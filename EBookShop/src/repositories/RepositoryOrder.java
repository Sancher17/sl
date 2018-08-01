package repositories;

import entities.Order;
import util.ID;

import java.util.ArrayList;
import java.util.List;

public class RepositoryOrder implements IRepository{

    private Long id = 0L;
    private List<Order> orders = new ArrayList<>();

    @Override
    public void add(Object obj) {
        id = findMaxId();
        id = ID.nextId(id);
        Order order = (Order) obj;
        order.setId(id);
        orders.add(order);
    }

    private long findMaxId(){
        long id = 0;
        for (Order order: orders){
            if (order.getId() > id){
                id = order.getId();
            }
        }
        return id;
    }

    @Override
    public Order getById(int id) {
        for(Order order: orders){
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        orders.removeIf(order -> order.getId() == id);
    }

    @Override
    public void deleteAll(List list) {
        list.clear();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderRepository{" +
                "orders=" + orders +
                '}';
    }
}
