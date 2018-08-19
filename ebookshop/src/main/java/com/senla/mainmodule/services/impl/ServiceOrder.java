package com.senla.mainmodule.services.impl;


import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.repositories.IRepository;
import com.senla.mainmodule.repositories.IRepositoryBook;
import com.senla.mainmodule.repositories.IRepositoryOrder;
import com.senla.mainmodule.repositories.impl.RepositoryBook;
import com.senla.mainmodule.repositories.impl.RepositoryOrder;
import com.senla.mainmodule.services.IServiceOrder;
import com.senla.mainmodule.util.comparators.order.ComparatorCompletedOrdersByDate;
import com.senla.mainmodule.util.comparators.order.ComparatorOrdersByPrice;
import com.senla.mainmodule.util.comparators.order.ComparatorOrdersByState;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServiceOrder extends Service implements IServiceOrder {

    private static final Logger log = Logger.getLogger(ServiceOrder.class);

    private IRepositoryOrder orders;
    private IRepositoryBook repositoryBook;
//    private static ServiceOrder instance = null;
//    public static ServiceOrder getInstance() {
//        if (instance == null) {
//            instance = new ServiceOrder();
//        }
//        return instance;
//    }

    public ServiceOrder(IRepositoryOrder orders, IRepositoryBook repositoryBook) {
        this.orders = orders;
        this.repositoryBook = repositoryBook;

    }


    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void addOrder(Long bookId) {
        try {
            Order newOrder = new Order((Book) repositoryBook.getById(bookId));
            orders.add(newOrder);
            notifyObservers("Добавлен заказ: " + newOrder);
        } catch (NullPointerException e) {
            notifyObservers("Книги с таким ID нет !!!");
            log.error("Добавление в книги с отстсвующим ID  " + e);
        }
    }

    @Override
    public void addOrder(Date startOrder, Long bookId) {
        try {
            Order newOrder = new Order(startOrder, (Book) repositoryBook.getById(bookId));
            orders.add(newOrder);
            notifyObservers("Добавлен заказ: " + newOrder);
        } catch (NullPointerException e) {
            notifyObservers("Книги с таким ID нет !!!");
            log.error("Добавление в книги с отстсвующим ID  " + e);
        }
    }

    @Override
    public void deleteOrderById(Long id) {
        if (orders.getById(id) != null) {
            notifyObservers("Удален заказ: " + orders.getById(id));
            orders.deleteById(id);
        } else {
            notifyObservers("Заказа с таким индексом нет !!!");
        }
    }

    @Override
    public void setCompleteOrderById(Long id) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1);
        Date todayMinusHour = cal.getTime();
        if (orders.getById(id) != null) {
            Order order = (Order) orders.getById(id);
            order.setCompletedOrder(true);
            order.setDateOfCompletedOrder(todayMinusHour);
            notifyObservers("Заказ отмечен выполненым \n" + orders.getById(id));
        } else {
            notifyObservers("Заказа с таким Id нет !!!");
        }
    }

    @Override
    public void setCompleteOrderById(Long id, Date dateOfCompleted) {
        Order order = (Order) orders.getById(id);
        order.setCompletedOrder(true);
        order.setDateOfCompletedOrder(dateOfCompleted);
    }

    @Override
    public void sortCompletedOrdersByDate() {
        orders.getAll().sort(new ComparatorCompletedOrdersByDate());
        notifyObservers("Заказы отсортированы по дате исполнения");
    }

    @Override
    public void sortOrdersByPrice() {
        orders.getAll().sort(new ComparatorOrdersByPrice());
        notifyObservers("Заказы отсортированы по цене");
    }

    @Override
    public void sortOrdersByState() {
        orders.getAll().sort(new ComparatorOrdersByState());
        notifyObservers("Заказы отсортированы по состоянию выполнения");
    }

    @Override
    public List<Order> getAll() {
        return orders.getAll();
    }

    @Override
    public List<Order> getCompletedOrders() {
        List<Order> ordersList = new ArrayList<>();
        for (Object obj : orders.getAll()) {
            Order order = (Order) obj;
            if (order.getCompletedOrder()) {
                ordersList.add(order);
            }
        }
        return ordersList;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate) {
        sortCompletedOrdersByDate();
        List<Order> ordersList = new ArrayList<>();
        for (Object obj : orders.getAll()) {
            Order order = (Order) obj;
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    if (order.getCompletedOrder()) {
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
        for (Object obj : orders.getAll()) {
            Order order = (Order) obj;
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    ordersList.add(order);
                }
            }
        }
        return ordersList;
    }

    @Override
    public Double getOrdersFullAmountByPeriod(Date startDate, Date endDate) {
        double amount = 0;
        for (Object obj : orders.getAll()) {
            Order order = (Order) obj;
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    amount = amount + order.getBook().getPrice();
                    amount = amount + order.getBook().getPrice();
                }
            }
        }
        return amount;
    }

    @Override
    public Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        int quantity = 0;
        for (Object obj : orders.getAll()) {
            Order order = (Order) obj;
            if (order.getCompletedOrder()) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    quantity++;
                }
            }
        }
        return quantity;
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = (Order) orders.getById(id);
        return order;
    }

    @Override
    public IRepositoryOrder getRepositoryOrder() {
        return orders;
    }

    @Override
    public Order cloneOrder(Long id) {
        Order order = (Order) orders.getById(id);
        Order tempOrder = null;
        try {
            if (order == null) {
                notifyObservers("Нет ордера с таким Id");
                return null;
            }
            tempOrder = order.clone();
            tempOrder.setBook(order.getBook());
            Long currentId = orders.getLastId();
            tempOrder.setId(currentId + 1L);
        } catch (CloneNotSupportedException ex) {
            log.error("Клонирование не поддерживается данной сущьностью");
            notifyObservers("Копирование не возможно");
        }
        return tempOrder;
    }

    @Override
    public List<Order> getRepo() {
        return orders.getAll();
    }

    @Override
    public void setRepo(List list) {
        orders.setAll(list);
        setLastId();
    }

    @Override
    public void setLastId() {
        Long id = 0L;
        for (Object obj : orders.getAll()) {
            Order order = (Order) obj;
            if (order.getId() > id) {
                id = order.getId();
            }
        }
        orders.setLastId(id);
    }
}
