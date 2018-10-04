package com.senla.services.impl;

import com.senla.db.IBookDao;
import com.senla.db.IOrderDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.services.IServiceOrder;
import com.senla.util.comparators.order.ComparatorOrdersByState;
import entities.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServiceOrder extends Service implements IServiceOrder {

    private static final Logger log = Logger.getLogger(ServiceOrder.class);

    private IOrderDao orderDao;
    private IBookDao bookDao;
    private IFileWorker fileWorker;

    private Connection connection = ConnectionDB.getConnection();

    public ServiceOrder() {
        this.orderDao = DependencyInjection.getBean(IOrderDao.class);
        this.bookDao = DependencyInjection.getBean(IBookDao.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addOrder(Order order) {
        orderDao.add(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        if (orderDao.getById(id) != null) {
            notifyObservers("Удален заказ: " + orderDao.getById(id));
            orderDao.deleteById(id);
        } else {
            notifyObservers("Заказа с таким индексом нет !!!");
        }
    }

    @Override
    public void setCompleteOrderById(Long id) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1);
        Date todayMinusHour = cal.getTime();
        if (orderDao.getById(id) != null) {
            Order order = orderDao.getById(id);
            order.setCompletedOrder(true);
            order.setDateOfCompletedOrder(todayMinusHour);
            notifyObservers("Заказ отмечен выполненым \n" + orderDao.getById(id));
        } else {
            notifyObservers("Заказа с таким Id нет !!!");
        }
    }

    @Override
    public void setCompleteOrderById(Long id, Date dateOfCompleted) {
        Order order = orderDao.getById(id);
        order.setCompletedOrder(true);
        order.setDateOfCompletedOrder(dateOfCompleted);
    }

    @Override
    public List<Order> sortCompletedOrdersByDate() {
        notifyObservers("Заказы отсортированы по дате исполнения\n");
        return getOrders("SELECT * FROM orders WHERE isCompletedOrder='1' ORDER BY dateOfCompletedOrder");
    }

    @Override
    public List<Order> sortOrdersByPrice() {
        notifyObservers("Заказы отсортированы по цене");
        return getOrders("SELECT o.id, o.dateOfStartedOrder, o.dateOfCompletedOrder, o.isCompletedOrder, b.price  FROM orders o join books b on o.book_id = b.id ORDER BY b.price");
    }

    @Override
    public List<Order> sortOrdersByState() {
        notifyObservers("Заказы отсортированы по состоянию выполнения");
        return getOrders("SELECT *  FROM orders ORDER BY isCompletedOrder");
    }

    private List<Order> getOrders(String sql) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            result.next();
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getLong("id"));
                order.setCompletedOrder(result.getBoolean("isCompletedOrder"));
                order.setDateOfCompletedOrder(result.getDate("dateOfCompletedOrder"));
                order.setDateOfStartedOrder(result.getDate("dateOfStartedOrder"));
                order.setBook(bookDao.getById(result.getLong("book_id")));
                orders.add(order);
            }
        } catch (SQLException e) {
            log.error("Не удалось получить данные из БД " + e);
        }
        return orders;
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public List<Order> getCompletedOrders() {
        return getOrders("SELECT * FROM orders WHERE isCompletedOrder='1';");
    }

    @Override
    public List<Order> getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Order> orders = new ArrayList<>();
        // TODO: 04.10.2018 поменять на wildcard
        String sql = "SELECT * FROM orders WHERE isCompletedOrder='1' AND dateOfStartedOrder BETWEEN " + "'"+sdf.format(startDate)+"'" + " AND " + "'"+sdf.format(endDate)+"'";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            result.next();
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getLong("id"));
                order.setCompletedOrder(result.getBoolean("isCompletedOrder"));
                order.setDateOfCompletedOrder(result.getDate("dateOfCompletedOrder"));
                order.setDateOfStartedOrder(result.getDate("dateOfStartedOrder"));
                order.setBook(bookDao.getById(result.getLong("book_id")));
                orders.add(order);
            }
        } catch (SQLException e) {
            log.error("Не удалось получить данные из БД " + e);
        }
        return orders;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate) {
        List<Order> newList = new ArrayList<>();
        List<Order> existList = sortOrdersByPrice();
        for (Order order : existList) {
            if (order.getDateOfCompletedOrder() != null) {
                if (order.getDateOfCompletedOrder().after(startDate) & order.getDateOfCompletedOrder().before(endDate)) {
                    newList.add(order);
                }
            }
        }
        return newList;
    }

    @Override
    public Double getFullAmountOfOrdersByPeriod(Date startDate, Date endDate) {
        double amount = 0;
        for (Order order : orderDao.getAll()) {
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
        for (Order order : orderDao.getAll()) {
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
        return orderDao.getById(id);
    }

    @Override
    public void exportToCsv() {
        super.writeToCsv(orderDao.getAll());
    }

    @Override
    public void importFromCsv() {
//        List<Order> importListFromFile = fileWorker.importListFromFile(PATH_ORDER_CSV, Order.class);
//        notifyObservers(PATH_ORDER_CSV);
//        merge(importListFromFile, orderDao);
    }

}
