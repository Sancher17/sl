package com.senla.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

    void add(Connection connection, T obj) throws SQLException;

    void deleteById(Connection connection, Long id) throws SQLException;

    T getById(Connection connection, Long id) throws SQLException;

    void update(Connection connection, T t) throws SQLException;

    List<T> getAll(Connection connection) throws SQLException;

    void addAll(Connection connection, List<T> notExistList) throws SQLException;
}
