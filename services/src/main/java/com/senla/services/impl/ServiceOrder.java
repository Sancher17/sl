package com.senla.services.impl;

import com.senla.db.IBookDao;
import com.senla.db.IOrderDao;
import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.services.IServiceOrder;
import entities.Order;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.PATH_ORDER_CSV;

public class ServiceOrder extends Service implements IServiceOrder {

    private static final Logger log = Logger.getLogger(ServiceOrder.class);

    private IOrderDao orderDao;
    private IBookDao bookDao;
    private IFileWorker fileWorker;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public ServiceOrder() {
        this.orderDao = DependencyInjection.getBean(IOrderDao.class);
        this.bookDao = DependencyInjection.getBean(IBookDao.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addOrder(Order order) {
        try {
            orderDao.add(connection, order);
        } catch (SQLException e) {
            log.error("Не удалось добавить данные в БД " + e);
            notifyObservers("Не удалось добавить данные в БД");
        }
    }

    @Override
    public void deleteOrderById(Long id) {
        try {
            if (orderDao.getById(connection, id) != null) {
                notifyObservers("Удален заказ: " + orderDao.getById(connection, id));
                orderDao.deleteById(connection, id);
            } else {
                notifyObservers("Заказа с таким индексом нет !!!");
            }
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
    }

    @Override
    public void setCompleteOrderById(Long id) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1);
        Date todayMinusHour = cal.getTime();
        try {
            if (orderDao.getById(connection, id) != null) {
                Order order = orderDao.getById(connection, id);
                order.setCompletedOrder(true);
                order.setDateOfCompletedOrder(todayMinusHour);
                notifyObservers("Заказ отмечен выполненым \n" + orderDao.getById(connection, id));
            } else {
                notifyObservers("Заказа с таким Id нет !!!");
            }
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
    }

    @Override
    public List<Order> getCompletedOrdersSortedByDate(){
        notifyObservers("Заказы отсортированы по дате исполнения\n");
        try {
            return orderDao.getCompletedSortedByDate(connection);
        }catch (SQLException e){
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }


    @Override
    public List<Order> getOrdersSortedByPrice() {
        notifyObservers("Заказы отсортированы по цене");
        try {
            return orderDao.getSortedByPrice(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Order> getOrdersSortedByState() {
        notifyObservers("Заказы отсортированы по состоянию выполнения");
        try {
            return orderDao.getSortedByState(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        try {
            return orderDao.getAll(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Order> getCompletedOrders(){
        try {
            return orderDao.getCompleted(connection);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate) {
        try {
            return orderDao.getCompletedSortedByDateOfPeriod(connection, startDate, endDate);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate) {
        try {
            return orderDao.getCompletedSortedByPriceOfPeriod(connection, startDate, endDate);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public Double getFullAmountOfOrdersByPeriod(Date startDate, Date endDate) {
        try {
            return orderDao.getFullAmountByPeriod(connection, startDate, endDate);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        try {
            return orderDao.getQuantityCompletedByPeriod(connection, startDate, endDate);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        try {
            return orderDao.getById(connection, id);
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public void exportToCsv() {
        try {
            super.writeToCsv(orderDao.getAll(connection));
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
    }

    @Override
    public void importFromCsv() {
        List<Order> importListFromFile = fileWorker.importListFromFile(PATH_ORDER_CSV, Order.class);
        notifyObservers(PATH_ORDER_CSV);
        merge(importListFromFile, orderDao);
    }
}
