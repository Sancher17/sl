package com.senla.db;

import entities.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface IRequestDao extends GenericDAO<Request> {

    List<Request> getSortedByQuantity(Connection connection) throws SQLException;

    List<Request> getSortedByAlphabet(Connection connection) throws SQLException;

    List<Request> getCompleted(Connection connection) throws SQLException;

    List<Request> getNotCompleted(Connection connection) throws SQLException;
}
