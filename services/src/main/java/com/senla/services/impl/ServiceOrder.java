package com.senla.services.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.hibernate.IBookDao;
import com.senla.hibernate.IOrderDao;
import com.senla.hibernate.util.HibernateUtil;
import com.senla.services.IServiceOrder;
import entities.Book;
import entities.Order;
import org.apache.log4j.Logger;
import java.lang.Exception;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.senla.constants.Constants.*;

public class ServiceOrder extends Service implements IServiceOrder {

    private static final Logger log = Logger.getLogger(ServiceOrder.class);

    private static final String ADDED_ORDER = "Добавлен заказ ";
    private static final String ORDER_DELETED = "Удален заказ ";
    private static final String CAN_NOT_UPDATE_ORDER = "Не удалось обновить заказ ";
    private static final String NO_ORDER_WITH_SUCH_INDEX = "Заказа с таким индексом нет !!!";
    private static final String ORDER_MARKED_AS_COMPLETE = "Заказ отмечен выполненым";
    private static final String ORDERS_SORTED_BY_DATE_OF_COMPLETE = "Заказы отсортированы по дате исполнения";
    private static final String ORDERS_SORTED_BY_PRICE = "Заказы отсортированы по цене";
    private static final String ORDERS_SORTED_BY_STATE = "Заказы отсортированы по состоянию выполнения";
    private static final String NO_BOOK_WITH_SUCH_ID = "Нет книги с таким ID\n";

    private IOrderDao orderDao;
    private IBookDao bookDao;
    private IFileWorker fileWorker;

    public ServiceOrder() {
        this.orderDao = DependencyInjection.getBean(IOrderDao.class);
        this.bookDao = DependencyInjection.getBean(IBookDao.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addOrder(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Book book = bookDao.getById(session, id, Book.class);
            if (book == null){
                notifyObservers(NO_BOOK_WITH_SUCH_ID);
                throw new Exception(NO_BOOK_WITH_SUCH_ID);
            }
            Order order = new Order(book);
            orderDao.add(session, order);
            notifyObservers(ADDED_ORDER + order);
        }  catch (Exception e) {
            log.error(CAN_NOT_ADD_DATA_TO_BD + e);
            notifyObservers(CAN_NOT_ADD_DATA_TO_BD);
            session.getTransaction().rollback();
            session.close();
        }
    }

    @Override
    public void deleteOrderById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Order order = orderDao.getById(session, id, Order.class);
            if (order != null) {
                orderDao.delete(session, order);
            } else {
                notifyObservers(NO_ORDER_WITH_SUCH_INDEX);
            }
            session.getTransaction().commit();
            session.close();
            notifyObservers(ORDER_DELETED + order);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
            session.getTransaction().rollback();
            session.close();
        }
    }

    @Override
    public void setCompleteOrderById(Long id) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1);
        Date nowMinusHour = cal.getTime();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Order order = orderDao.getById(session, id, Order.class);
            if (order != null) {
                order.setCompletedOrder(true);
                order.setDateOfCompletedOrder(nowMinusHour);
                orderDao.update(session, order);
                notifyObservers(ORDER_MARKED_AS_COMPLETE + order);
            } else {
                notifyObservers(NO_ORDER_WITH_SUCH_INDEX);
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            log.error(CAN_NOT_UPDATE_ORDER + e);
            notifyObservers(CAN_NOT_UPDATE_ORDER);
            session.getTransaction().rollback();
            session.close();
        }
    }

    @Override
    public List<Order> getCompletedOrdersSortedByDate() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            List<Order> orders = orderDao.getCompletedSortedByDate(session);
            notifyObservers(ORDERS_SORTED_BY_DATE_OF_COMPLETE);
            return orders;
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Order> getOrdersSortedByPrice() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            List<Order> orders = orderDao.getSortedByPrice(session);
            notifyObservers(ORDERS_SORTED_BY_PRICE);
            return orders;
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Order> getOrdersSortedByState() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            List<Order> orders = orderDao.getSortedByState(session);
            notifyObservers(ORDERS_SORTED_BY_STATE);
            return orders;
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return orderDao.getAll(session, Order.class);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Order> getCompletedOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return orderDao.getCompleted(session);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByDateOfPeriod(Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return orderDao.getCompletedSortedByDateOfPeriod(session, startDate, endDate);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Order> getCompletedOrdersSortedByPriceOfPeriod(Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return orderDao.getCompletedSortedByPriceOfPeriod(session, startDate, endDate);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public Double getFullAmountOfOrdersByPeriod(Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return orderDao.getFullAmountByPeriod(session, startDate, endDate);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public Integer getQuantityCompletedOrdersByPeriod(Date startDate, Date endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return orderDao.getQuantityCompletedByPeriod(session, startDate, endDate);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return orderDao.getById(session, id, Order.class);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public void copyOrder(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            orderDao.copyOrder(session, id);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void exportToCsv() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            super.writeToCsv(orderDao.getAll(session, Order.class));
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + " / " + CAN_NOT_WRITE_DATA_TO_FILE + e);
            notifyObservers(NO_DATA_FROM_BD + " / " + CAN_NOT_WRITE_DATA_TO_FILE);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Order> importListFromFile = fileWorker.importListFromFile(PATH_ORDER_CSV, session, Order.class);
            notifyObservers(PATH_ORDER_CSV);
            merge(session, importListFromFile, orderDao, Order.class);
        } catch (Exception e) {
            log.error(CAN_NOT_ADD_DATA_FROM_FILE + e);
            notifyObservers(CAN_NOT_ADD_DATA_FROM_FILE);
        }
    }
}
