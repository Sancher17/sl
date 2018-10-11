package com.senla.db.impl;

import com.senla.db.IRequestDao;
import com.senla.db.connection.ConnectionDB;
import entities.Request;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDao implements IRequestDao {

    private static final String ID = "id";
    private static final String REQUIRE_NAME_BOOK = "requireNameBook";
    private static final String REQUIRE_IS_COMPLETED = "requireIsCompleted";
    private static final String REQUIRE_QUANTITY = "requireQuantity";

    @Override
    public void add(Connection connection, Request request) throws SQLException {
        String sql = "INSERT INTO request (requireNameBook, requireIsCompleted, requireQuantity) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, request.getRequireNameBook());
            statement.setBoolean(2, request.getRequireIsCompleted());
            statement.setInt(3, request.getRequireQuantity());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(Connection connection, Long id) throws SQLException {
        String sql = "DELETE FROM request WHERE id =" + id;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        }
    }

    @Override
    public Request getById(Connection connection, Long id) throws SQLException {
        String sql = "SELECT * FROM request WHERE id="+id;
        Request request = new Request();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            if(result.next()){
                request.setId(result.getLong(ID));
                request.setRequireNameBook(result.getString(REQUIRE_NAME_BOOK));
                request.setRequireIsCompleted(result.getBoolean(REQUIRE_IS_COMPLETED));
                request.setRequireQuantity(result.getInt(REQUIRE_QUANTITY));
                return request;
            }
        }
        return null;
    }

    @Override
    public void update(Connection connection, Request request) throws SQLException{
        String sql = "UPDATE request SET requireQuantity=?, requireIsCompleted=? WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, request.getRequireQuantity());
            statement.setBoolean(2, request.getRequireIsCompleted());
            statement.setLong(3,request.getId());
            statement.execute();
        }
    }


    @Override
    public List<Request> getAll(Connection connection) throws SQLException{
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM request";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Request request = new Request();
                request.setId(result.getLong(ID));
                request.setRequireNameBook(result.getString(REQUIRE_NAME_BOOK));
                request.setRequireIsCompleted(result.getBoolean(REQUIRE_IS_COMPLETED));
                request.setRequireQuantity(result.getInt(REQUIRE_QUANTITY));
                requests.add(request);
            }
        }
        return requests;
    }

    @Override
    public void addAll(Connection connection, List<Request> requests) throws SQLException{
        for (Request request : requests) {
            add(connection, request);
        }
    }

    @Override
    public List<Request> getSortedByQuantity(Connection connection) throws SQLException {
        return getRequests(connection, "SELECT * FROM request ORDER BY requireQuantity;");
    }

    @Override
    public List<Request> getSortedByAlphabet(Connection connection) throws SQLException {
        return getRequests(connection,"SELECT * FROM request ORDER BY requireNameBook;");
    }

    @Override
    public List<Request> getCompleted(Connection connection) throws SQLException {
        return getRequests(connection, "SELECT * FROM request WHERE requireIsCompleted='1';");
    }

    @Override
    public List<Request> getNotCompleted(Connection connection) throws SQLException {
        return getRequests(connection, "SELECT * FROM request WHERE requireIsCompleted='0';");
    }

    private List<Request> getRequests(Connection connection, String sql) throws SQLException {
        List<Request> requests = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()){
                Request request = new Request();
                request.setId(result.getLong(ID));
                request.setRequireNameBook(result.getString(REQUIRE_NAME_BOOK));
                request.setRequireIsCompleted(result.getBoolean(REQUIRE_IS_COMPLETED));
                request.setRequireQuantity(result.getInt(REQUIRE_QUANTITY));
                requests.add(request);
            }
        }
        return requests;
    }
}
