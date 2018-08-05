package com.senla.mainmodule.services.impl;


import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.repositories.IRepositoryBook;
import com.senla.mainmodule.repositories.IRepositoryOrder;
import com.senla.mainmodule.repositories.impl.RepositoryBook;
import com.senla.mainmodule.repositories.impl.RepositoryOrder;
import com.senla.mainmodule.services.IServiceOrder;
import com.senla.mainmodule.util.comparators.order.ComparatorCompletedOrdersByDate;
import com.senla.mainmodule.util.comparators.order.ComparatorOrdersByPrice;
import com.senla.mainmodule.util.comparators.order.ComparatorOrdersByState;
import org.apache.log4j.Logger;
import java.util.*;

public class ServiceOrder extends Service implements IServiceOrder {

    private static final Logger log = Logger.getLogger(ServiceOrder.class);
    private IRepositoryOrder orders = RepositoryOrder.getInstance();
    private IRepositoryBook repositoryBook = RepositoryBook.getInstance();
    private static ServiceOrder instance = null;

    public static ServiceOrder getInstance() {
        if (instance == null) {
            instance = new ServiceOrder();
        }
        return instance;
    }

    private ServiceOrder() {
    }

    @Override
    public void setCompleteOrderById(Long id) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, -1);
            Date todayMinusHour = cal.getTime();
            orders.getById(id).setCompletedOrder(true);
            orders.getById(id).setDateOfCompletedOrder(todayMinusHour);
            notifyObservers("Заказ отмечен выполненым \n" + orders.getById(id));
        } catch (NullPointerException e) {
            notifyObservers("Заказа с таким ID нет !!!");
            log.error("setCompleteOrderById " + e);
        }
    }

    @Override
    public void setCompleteOrderById(Long id, Date dateOfCompleted) {
        orders.getById(id).setCompletedOrder(true);
        orders.getById(id).setDateOfCompletedOrder(dateOfCompleted);
    }

    public Order getOrder(int id) {
        return orders.getOrders().get(id);
    }

    @Override
    public void addOrder(Long bookId) {
        try {
            Order newOrder = new Order(repositoryBook.getById(bookId));
            orders.add(newOrder);
            notifyObservers("Добавлен заказ: " + newOrder);
        } catch (NullPointerException e) {
            notifyObservers("Книги с таким ID нет !!!");
            log.error("addOrder " + e);
        }
    }

    @Override
    public void addOrder(Date startOrder, Long bookId) {
        try {
            Order newOrder = new Order(startOrder, repositoryBook.getById(bookId));
            orders.add(newOrder);
            notifyObservers("Добавлен заказ: " + newOrder);
        } catch (NullPointerException e) {
            notifyObservers("Книги с таким ID нет !!!");
            log.error("addOrder " + e);
        }
    }

    @Override
    public List<Order> getCompletedOrders() {
        List<Order> ordersList = new ArrayList<>();
        for (Order order : orders.getOrders()) {
            if (order.isCompletedOrder()) {
                ordersList.add(order);
            }
        }
        return ordersList;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate) {
        sortCompletedOrdersByDate();
        List<Order> ordersList = new ArrayList<>();
        for (Order order : orders.getOrders()) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    if (order.isCompletedOrder()) {
                        ordersList.add(order);
                    }
                }
            }
        }
        return ordersList;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate) {
        sortOrdersByPrice();
        List<Order> ordersList = new ArrayList<>();
        for (Order order : orders.getOrders()) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    ordersList.add(order);
                }
            }
        }
        return ordersList;
    }

    @Override
    public IRepositoryOrder getRepositoryOrder() {
        return orders;
    }

    @Override
    public void deleteOrderById(Long id) {
        try {
            notifyObservers("Удален заказ: " + orders.getById(id));
            orders.deleteById(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            notifyObservers("Заказа с таким индексом нет !!!");
        }
    }

    @Override
    public List<Order> getAll() {
        return orders.getOrders();
    }

    @Override
    public void sortCompletedOrdersByDate() {
        orders.getOrders().sort(new ComparatorCompletedOrdersByDate());
        notifyObservers("Заказы отсортированы по дате исполнения");
    }

    @Override
    public void sortOrdersByPrice() {
        orders.getOrders().sort(new ComparatorOrdersByPrice());
        notifyObservers("Заказы отсортированы по цене");
    }

    @Override
    public void sortOrdersByState() {
        orders.getOrders().sort(new ComparatorOrdersByState());
        notifyObservers("Заказы отсортированы по состоянию выполнения");
    }

    @Override
    public Double getOrdersFullAmountByPeriod(Date startDate, Date endDate) {
        double amount = 0;
        for (Order order : orders.getOrders()) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    amount = amount + order.getBook().getPrice();
                }
            }
        }
        return amount;
    }

    @Override
    public Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        int quantity = 0;
        for (Order order : orders.getOrders()) {
            if (order.isCompletedOrder()) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    quantity++;
                }
            }
        }
        return quantity;
    }

    @Override
    public Order getOrderById(Long id) {
        return orders.getById(id);
    }
}
