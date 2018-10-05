package com.senla.services.impl;

import com.senla.db.IBookDao;
import com.senla.db.IOrderDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.services.IServiceOrder;
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
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        return getOrdersByPeriod(startDate, endDate, "SELECT o.id, dateOfStartedOrder, dateOfCompletedOrder, b.price, isCompletedOrder, book_id FROM orders o JOIN books b on o.book_id = b.id WHERE isCompletedOrder='1' AND dateOfStartedOrder BETWEEN ? AND ? ORDER BY dateOfCompletedOrder;");
    }

    @Override
    public List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate) {
        return getOrdersByPeriod(startDate, endDate, "SELECT o.id, dateOfStartedOrder, dateOfCompletedOrder, b.price, isCompletedOrder, book_id FROM orders o JOIN books b on o.book_id = b.id WHERE isCompletedOrder='1' AND dateOfStartedOrder BETWEEN ? AND ? ORDER BY b.price;");
    }

    private List<Order> getOrders(String sql) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
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

    private List<Order> getOrdersByPeriod(Date startDate, Date endDate, String sql) {
        List<Order> orders = new ArrayList<>();
        ResultSet result = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sdf.format(startDate));
            statement.setString(2, sdf.format(endDate));
            result = statement.executeQuery();
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
        } finally {
            try {
                if (result !=null)result.close();
            } catch (SQLException e) {
                log.error("Ошибка при закрытии: result " + e);
            }
        }
        return orders;
    }

    @Override
    public Double getFullAmountOfOrdersByPeriod(Date startDate, Date endDate) {
        Double amount = null;
        String sql = "SELECT SUM(b.price) FROM orders o JOIN books b on o.book_id = b.id WHERE dateOfStartedOrder BETWEEN ? AND ?;";
        ResultSet result = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sdf.format(startDate));
            statement.setString(2, sdf.format(endDate));
            result = statement.executeQuery();
            result.next();
            amount = result.getDouble(1);
        } catch (SQLException e) {
            log.error("Не удалось получить данные из БД " + e);
        } finally {
            try {
                if (result !=null)result.close();
            } catch (SQLException e) {
                log.error("Ошибка при закрытии: result " + e);
            }
        }
        return amount;
    }

    @Override
    public Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        Integer sum = null;
        String sql = "SELECT SUM(isCompletedOrder) FROM orders WHERE isCompletedOrder='1' AND dateOfCompletedOrder BETWEEN ? AND ?;";
        ResultSet result = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sdf.format(startDate));
            statement.setString(2, sdf.format(endDate));
            result = statement.executeQuery();
            result.next();
            sum = result.getInt(1);
        } catch (SQLException e) {
            log.error("Не удалось получить данные из БД " + e);
        } finally {
            try {
                if (result !=null)result.close();
            } catch (SQLException e) {
                log.error("Ошибка при закрытии: result " + e);
            }
        }
        return sum;
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
