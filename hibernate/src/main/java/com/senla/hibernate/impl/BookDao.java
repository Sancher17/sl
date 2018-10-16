package com.senla.hibernate.impl;

import com.senla.hibernate.IBookDao;
import entities.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class BookDao implements IBookDao {

    private static final String NAME_BOOK = "nameBook";

    @Override
    public void add(Session session, Book book) {
        session.save(book);
    }

    @Override
    public Book getByName(Session session, String name) throws NoResultException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(NAME_BOOK), name));
        Query<Book> query = session.createQuery(criteria);
        Book book = query.getSingleResult();
//        List<Book> books = query.list(); //list
        return book;
    }

    @Override
    public List<Book> getSortedByPrice(Session session) {
        return null;
    }

    @Override
    public List<Book> getSortedByAlphabet(Session session) {
        return null;
    }

    @Override
    public List<Book> getSortedByDatePublication(Session session) {
        return null;
    }

    @Override
    public List<Book> getSortedByAvailability(Session session) {
        return null;
    }

    @Override
    public List<Book> getPeriodMoreSixMonthByDate(Session session) {
        return null;
    }

    @Override
    public List<Book> getPeriodMoreSixMonthByPrice(Session session) {
        return null;
    }

    @Override
    public List<Book> getNewBooks(Session session, Date periodOfMonth) {
        return null;
    }

    @Override
    public void deleteById(Session session, Long id) {

    }

    @Override
    public Book getById(Session session, Long id) {
        return session.get(Book.class, id);
    }

    @Override
    public void update(Session session, Book book) {

    }

    @Override
    public List<Book> getAll(Session session) {
        // TODO: 15.10.2018 сделать через Criteria
        return (List<Book>) session.createQuery( "from Book" ).list();
    }

    @Override
    public void addAll(Session session, List<Book> notExistList) {

    }
}
