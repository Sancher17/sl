package com.senla.services.impl;

import com.senla.db.IOrderDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.services.IServiceOrder;
import entities.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.PATH_ORDER_CSV;

public class ServiceOrder extends Service implements IServiceOrder {

    private static final Logger log = Logger.getLogger(ServiceOrder.class);

    private static final String ORDER_DELETED = "Удален заказ: ";
    private static final String NO_ORDER_WITH_SUCH_INDEX = "Заказа с таким индексом нет !!!";
    private static final String ORDER_MARKED_AS_COMPLETE = "Заказ отмечен выполненым";
    private static final String ORDERS_SORTED_BY_DATE_OF_COMPLETE = "Заказы отсортированы по дате исполнения";
    private static final String ORDERS_SORTED_BY_PRICE = "Заказы отсортированы по цене";
    private static final String ORDERS_SORTED_BY_STATE = "Заказы отсортированы по состоянию выполнения";

    private IOrderDao orderDao;
    private IFileWorker fileWorker;


    public ServiceOrder() {
        this.orderDao = DependencyInjection.getBean(IOrderDao.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addOrder(Order order) {
        Connection connection = ConnectionDB.getConnection();
        try {
            connection.setAutoCommit(false);
            orderDao.add(connection, order);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            String message = CAN_NOT_ADD_DATA_TO_BD;
            log.error(message + e);
            notifyObservers(message);
            try {
                connection.setAutoCommit(true);
                connection.rollback();
            } catch (SQLException e1) {
                log.error(CAN_NOT_DO_ROLLBACK + e1);
            }
        }
    }

    @Override
    public void deleteOrderById(Long id) {
        Connection connection = ConnectionDB.getConnection();
        try {
            if (orderDao.getById(connection, id) != null) {
                notifyObservers(ORDER_DELETED + orderDao.getById(connection, id));
                orderDao.deleteById(connection, id);
            } else {
                notifyObservers(NO_ORDER_WITH_SUCH_INDEX);
            }
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
    }

    @Override
    public void setCompleteOrderById(Long id) {
        Connection connection = ConnectionDB.getConnection();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1);
        Date todayMinusHour = cal.getTime();
        try {
            if (orderDao.getById(connection, id) != null) {
                Order order = orderDao.getById(connection, id);
                order.setCompletedOrder(true);
                order.setDateOfCompletedOrder(todayMinusHour);
                notifyObservers(ORDER_MARKED_AS_COMPLETE + orderDao.getById(connection, id));
            } else {
                notifyObservers(NO_ORDER_WITH_SUCH_INDEX);
            }
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
    }

    @Override
    public List<Order> getCompletedOrdersSortedByDate() {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(ORDERS_SORTED_BY_DATE_OF_COMPLETE);
        try {
            return orderDao.getCompletedSortedByDate(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }


    @Override
    public List<Order> getOrdersSortedByPrice() {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(ORDERS_SORTED_BY_PRICE);
        try {
            return orderDao.getSortedByPrice(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public List<Order> getOrdersSortedByState() {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(ORDERS_SORTED_BY_STATE);
        try {
            return orderDao.getSortedByState(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        Connection connection = ConnectionDB.getConnection();
        try {
            return orderDao.getAll(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public List<Order> getCompletedOrders() {
        Connection connection = ConnectionDB.getConnection();
        try {
            return orderDao.getCompleted(connection);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate) {
        Connection connection = ConnectionDB.getConnection();
        try {
            return orderDao.getCompletedSortedByDateOfPeriod(connection, startDate, endDate);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate) {
        Connection connection = ConnectionDB.getConnection();
        try {
            return orderDao.getCompletedSortedByPriceOfPeriod(connection, startDate, endDate);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public Double getFullAmountOfOrdersByPeriod(Date startDate, Date endDate) {
        Connection connection = ConnectionDB.getConnection();
        try {
            return orderDao.getFullAmountByPeriod(connection, startDate, endDate);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        Connection connection = ConnectionDB.getConnection();
        try {
            return orderDao.getQuantityCompletedByPeriod(connection, startDate, endDate);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        Connection connection = ConnectionDB.getConnection();
        try {
            return orderDao.getById(connection, id);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
        return null;
    }

    @Override
    public void copyOrder(Long id) {
        Connection connection = ConnectionDB.getConnection();
        try {
            orderDao.copyOrder(connection, id);
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
    }

    @Override
    public void exportToCsv() {
        Connection connection = ConnectionDB.getConnection();
        try {
            super.writeToCsv(orderDao.getAll(connection));
        } catch (SQLException e) {
            String message = NO_DATA_FROM_BD;
            log.error(message + e);
            notifyObservers(message);
        }
    }

    @Override
    public void importFromCsv() {
        List<Order> importListFromFile = fileWorker.importListFromFile(PATH_ORDER_CSV, Order.class);
        notifyObservers(PATH_ORDER_CSV);
        merge(importListFromFile, orderDao);
    }
}
