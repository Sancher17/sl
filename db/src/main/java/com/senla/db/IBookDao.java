package com.senla.db;

import entities.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public interface IBookDao extends GenericDAO<Book> {

    Book getByName(Connection connection, String name) throws SQLException;

    List<Book> getSortedByPrice(Connection connection) throws SQLException;

    List<Book> getSortedByAlphabet(Connection connection) throws SQLException;

    List<Book> getSortedByDatePublication(Connection connection) throws SQLException;

    List<Book> getSortedByAvailability(Connection connection) throws SQLException;

    List<Book> getPeriodMoreSixMonthByDate(Connection connection) throws SQLException;

    List<Book> getNewBooks(Connection connection, Date periodOfMonth) throws SQLException;
}
