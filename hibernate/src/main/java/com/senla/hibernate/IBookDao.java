package com.senla.hibernate;

import entities.Book;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public interface IBookDao extends GenericDAO<Book> {

    Book getByName(Session session, String name);

    List<Book> getSortedByPrice(Session session);

    List<Book> getSortedByAlphabet(Session session);

    List<Book> getSortedByDatePublication(Session session);

    List<Book> getSortedByAvailability(Session session);

    List<Book> getPeriodMoreSixMonthByDate(Session session);

    List<Book> getPeriodMoreSixMonthByPrice(Session session);

    List<Book> getNewBooks(Session session, Date periodOfMonth);


}
