package com.senla.services.impl;

import com.senla.db.IRequestDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.services.IServiceRequest;
import entities.Request;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.PATH_REQUEST_CSV;

public class ServiceRequest extends Service implements IServiceRequest {

    private static final Logger log = Logger.getLogger(ServiceRequest.class);
    private IRequestDao requestDao;
    private IFileWorker fileWorker;

    private Connection connection = ConnectionDB.getConnection();

    public ServiceRequest() {
        this.requestDao = DependencyInjection.getBean(IRequestDao.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addBookRequest(Request request) {
        notifyObservers("Добавлен запрос на книгу: " + request.getRequireNameBook());
        boolean exist = false;
        try {
            for (Request aRequest : requestDao.getAll(connection)) {
                if (aRequest != null) {
                    if (aRequest.getRequireNameBook().equals(request.getRequireNameBook())) {
                        exist = true;
                        aRequest.setRequireQuantity(aRequest.getRequireQuantity() + 1);
                        requestDao.update(connection, aRequest);
                    }
                }
            }
            if (!exist) {
                request.setRequireQuantity(1);
                requestDao.add(connection, request);
            }
        }catch (SQLException e){
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
    }

    @Override
    public List<Request> sortRequestsByQuantity() {
        notifyObservers("Запросы отсортированы по количеству запросов");
        return getRequests("SELECT * FROM request ORDER BY requireQuantity");
    }

    @Override
    public List<Request> sortRequestsByAlphabet() {
        notifyObservers("Запросы отсортированы по алфавиту");
        return getRequests("SELECT * FROM request ORDER BY requireNameBook");
    }

    private List<Request> getRequests(String sql) {
        List<Request> requests = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Request request = new Request();
                request.setId(result.getLong("id"));
                request.setRequireNameBook(result.getString("requireNameBook"));
                request.setRequireIsCompleted(result.getBoolean("requireIsCompleted"));
                request.setRequireQuantity(result.getInt("requireQuantity"));
                requests.add(request);
            }
        } catch (SQLException e) {
            log.error("Не удалось получить данные из БД " + e);
        }
        return requests;
    }

    @Override
    public List<Request> getAll() {
        try {
            return requestDao.getAll(connection);
        }catch (SQLException e){
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Request> getCompletedRequests() {
        List<Request> requests = new ArrayList<>();
        try {
            for (Request request : requestDao.getAll(connection)) {
                if (request.getRequireIsCompleted()) {
                    requests.add(request);
                }
            }
            return requests;
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public List<Request> getNotCompletedRequests() {
        List<Request> requests = new ArrayList<>();
        try {
            for (Request request : requestDao.getAll(connection)) {
                if (!request.getRequireIsCompleted()) {
                    requests.add(request);
                }
            }
            return requests;
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
        return null;
    }

    @Override
    public void exportToCsv() {
        try {
            super.writeToCsv(requestDao.getAll(connection));
        } catch (SQLException e) {
            log.error("Не удалось получить данные с БД " + e);
            notifyObservers("Не удалось получить данные с БД");
        }
    }

    @Override
    public void importFromCsv() {
        List<Request> importListFromFile = fileWorker.importListFromFile(PATH_REQUEST_CSV, Request.class);
        notifyObservers(PATH_REQUEST_CSV);
        merge(importListFromFile, requestDao);
    }
}
