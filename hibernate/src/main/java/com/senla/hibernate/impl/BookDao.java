package com.senla.hibernate.impl;

import com.senla.hibernate.IBookDao;
import com.senla.util.date.DateUtil;
import entities.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class BookDao implements IBookDao {

    private static final String NAME_BOOK = "nameBook";
    private static final String PRICE = "price";
    private static final String DATE_OF_PUBLICATION = "dateOfPublication";
    private static final String IS_AVAILABLE = "isAvailable";
    private static final String DATE_ADDED_BOOK_TO_STORE = "dateAddedBookToStore";

    @Override
    public List<Book> getSortedByPrice(Session session) {
        return getBooksSortedByOrder(session, PRICE);
    }

    @Override
    public List<Book> getSortedByAlphabet(Session session) {
        return getBooksSortedByOrder(session, NAME_BOOK);
    }

    @Override
    public List<Book> getSortedByDatePublication(Session session) {
        return getBooksSortedByOrder(session, DATE_OF_PUBLICATION);
    }

    @Override
    public List<Book> getSortedByAvailability(Session session) {
        return getBooksSortedByOrder(session, IS_AVAILABLE);
    }

    @Override
    public List<Book> getPeriodMoreSixMonthByDate(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root)
                .where(builder.lessThan(root.get(DATE_ADDED_BOOK_TO_STORE), DateUtil.minusMonths(6)))
                .orderBy(builder.asc(root.get(DATE_ADDED_BOOK_TO_STORE)));
        Query<Book> query = session.createQuery(criteria);
        return query.list();
    }

    @Override
    public List<Book> getPeriodMoreSixMonthByPrice(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root)
                .where(builder.lessThan(root.get(DATE_ADDED_BOOK_TO_STORE), DateUtil.minusMonths(6)))
                .orderBy(builder.asc(root.get(PRICE)));
        Query<Book> query = session.createQuery(criteria);
        return query.list();
    }

    @Override
    public List<Book> getNewBooks(Session session, Date periodOfMonth) {
        return session.createQuery(
                "select b " +
                        "from Book b " +
                        "where b.dateAddedBookToStore > : periodOfMonth " +
                        "order by b.dateAddedBookToStore",
                Book.class)
                .setParameter("periodOfMonth", periodOfMonth, TemporalType.DATE)
                .getResultList();
    }

    private List<Book> getBooksSortedByOrder(Session session, String order) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root)
                .orderBy(builder.asc(root.get(order)));
        Query<Book> query = session.createQuery(criteria);
        return query.list();
    }
}
