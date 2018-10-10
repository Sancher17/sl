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

    private static final Logger log = Logger.getLogger(RequestDao.class);
    private Connection connection;

    public RequestDao() {
        this.connection = ConnectionDB.getConnection();
    }

    @Override
    public void add(Connection connection, Request request) {
        String sql = "INSERT INTO request (requireNameBook, requireIsCompleted, requireQuantity) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, request.getRequireNameBook());
            statement.setBoolean(2, request.getRequireIsCompleted());
            statement.setInt(3, request.getRequireQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Не удачная попытка добавить Request в БД - " + e);
        }
    }

    @Override
    public void deleteById(Connection connection, Long id) {
        String sql = "DELETE FROM request WHERE id =" + id;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Не удачная попытка удалить Request из БД - " + e);
        }
    }

    @Override
    public Request getById(Connection connection, Long id) {
        String sql = "SELECT * FROM request WHERE id="+id;
        Request request = new Request();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            if(result.next()){
                request.setId(result.getLong("id"));
                request.setRequireNameBook(result.getString("requireNameBook"));
                request.setRequireIsCompleted(result.getBoolean("requireIsCompleted"));
                request.setRequireQuantity(result.getInt("requireQuantity"));
                return request;
            }
        } catch (SQLException e) {
            log.error("Не удачная попытка получить Request из БД - " + e);
        }
        return null;
    }

    @Override
    public void update(Connection connection, Request request) {
        String sql = "UPDATE request SET requireQuantity=?, requireIsCompleted=? WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, request.getRequireQuantity());
            statement.setBoolean(2, request.getRequireIsCompleted());
            statement.setLong(3,request.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error("Не удачная попытка обновить Request - " + e);
        }
    }


    @Override
    public List<Request> getAll(Connection connection) {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM request";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Request request = new Request();
                request.setId(result.getLong("id"));
                request.setRequireNameBook(result.getString("requireNameBook"));
                request.setRequireIsCompleted(result.getBoolean("requireIsCompleted"));
                request.setRequireQuantity(result.getInt("requireQuantity"));
                requests.add(request);
            }
        } catch (SQLException e) {
            log.error("Не удачная попытка получить список Request из БД - " + e);
        }
        return requests;
    }

    @Override
    public void addAll(Connection connection, List<Request> requests) {

    }
}
