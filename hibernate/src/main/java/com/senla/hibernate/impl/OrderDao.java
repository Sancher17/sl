package com.senla.hibernate.impl;

import com.senla.hibernate.IOrderDao;
import entities.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class OrderDao implements IOrderDao {

    private static final String DATE_OF_STARTED_ORDER = "dateOfStartedOrder";
    private static final String DATE_OF_COMPLETED_ORDER = "dateOfCompletedOrder";
    private static final String IS_COMPLETED_ORDER = "isCompletedOrder";
    private static final String ID = "id";


    @Override
    public List<Order> getCompletedSortedByDate(Session session) {
        return getCompletedOrdersSortedByOrder(session, DATE_OF_STARTED_ORDER);
    }

    @Override
    public List<Order> getSortedByPrice(Session session) {
        return session.createQuery(
                "select o " +
                        "from Order o " +
                        "order by o.book.price",
                Order.class)
                .getResultList();
    }

    @Override
    public List<Order> getSortedByState(Session session) {
        return getOrdersSortedByOrder(session, IS_COMPLETED_ORDER);
    }

    @Override
    public List<Order> getCompleted(Session session) {
        return getCompletedOrdersSortedByOrder(session, ID);
    }

    @Override
    public List<Order> getCompletedSortedByDateOfPeriod(Session session, Date startDate, Date endDate) {
        return getCompletedOrdersSortedByOrderAndPeriod(session, startDate, endDate, DATE_OF_STARTED_ORDER);
    }

    @Override
    public List<Order> getCompletedSortedByPriceOfPeriod(Session session, Date startDate, Date endDate) {
        return session.createQuery(
                "select o " +
                        "from Order o " +
                        "where o.isCompletedOrder = true " +
                        "and o.dateOfCompletedOrder > :startDate " +
                        "and o.dateOfCompletedOrder < :endDate " +
                        "order by o.book.price",
                Order.class)
                .setParameter("startDate", startDate, TemporalType.DATE)
                .setParameter("endDate", endDate, TemporalType.DATE)
                .getResultList();
    }

    @Override
    public Double getFullAmountByPeriod(Session session, Date startDate, Date endDate) {
        return session.createQuery(
                "select sum(book.price) " +
                        "from Order o " +
                        "where o.dateOfCompletedOrder > :startDate " +
                        "and o.dateOfCompletedOrder < :endDate ",
                Double.class)
                .setParameter("startDate", startDate, TemporalType.DATE)
                .setParameter("endDate", endDate, TemporalType.DATE)
                .getSingleResult();
    }

    @Override
    public Integer getQuantityCompletedByPeriod(Session session, Date startDate, Date endDate) {
        return Math.toIntExact(session.createQuery(
                "select count (o) " +
                        "from Order o " +
                        "where o.isCompletedOrder = true " +
                        "and o.dateOfCompletedOrder > :startDate " +
                        "and o.dateOfCompletedOrder < :endDate ",
                Long.class)
                .setParameter("startDate", startDate, TemporalType.DATE)
                .setParameter("endDate", endDate, TemporalType.DATE)
                .getSingleResult());
    }

    @Override
    public void copyOrder(Session session, Long id) {
        Order order = getById(session, id, Order.class);
        add(session, order);
    }


    private List<Order> getOrdersSortedByOrder(Session session, String order) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.select(root)
                .orderBy(builder.asc(root.get(order)));
        Query<Order> query = session.createQuery(criteria);
        return query.list();
    }

    private List<Order> getCompletedOrdersSortedByOrder(Session session, String order) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.select(root)
                .where(builder.isTrue(root.get(IS_COMPLETED_ORDER)))
                .orderBy(builder.asc(root.get(order)));
        Query<Order> query = session.createQuery(criteria);
        return query.list();
    }

    private List<Order> getCompletedOrdersSortedByOrderAndPeriod(Session session, Date startDate, Date endDate, String order) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.select(root)
                .where(builder.isTrue(root.get(IS_COMPLETED_ORDER)))
                .where(builder.greaterThan(root.get(DATE_OF_COMPLETED_ORDER), startDate))
                .where(builder.lessThan(root.get(DATE_OF_COMPLETED_ORDER), endDate))
                .orderBy(builder.asc(root.get(order)));
        Query<Order> query = session.createQuery(criteria);
        return query.list();
    }
}