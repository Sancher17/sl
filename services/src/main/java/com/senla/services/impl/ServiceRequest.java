package com.senla.services.impl;

import com.senla.db.IRequestDao;
import com.senla.db.connection.ConnectionDB;
import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.services.IServiceRequest;
import entities.Request;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.senla.constants.Constants.*;

public class ServiceRequest extends Service implements IServiceRequest {

    private static final Logger log = Logger.getLogger(ServiceRequest.class);

    private static final String ADDED_REQUEST = "Добавлен запрос на книгу: ";
    private static final String REQUESTS_SORTED_BY_QUANTITY = "Запросы отсортированы по количеству запросов";
    private static final String REQUESTS_SORTED_BY_ALPHABET = "Запросы отсортированы по алфавиту";

    private IRequestDao requestDao;
    private IFileWorker fileWorker;

    public ServiceRequest() {
        this.requestDao = DependencyInjection.getBean(IRequestDao.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addBookRequest(Request request) {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(ADDED_REQUEST + request.getRequireNameBook());
        boolean exist = false;
        try {
            connection.setAutoCommit(false);
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
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(CAN_NOT_ADD_DATA_TO_BD + e);
            notifyObservers(CAN_NOT_ADD_DATA_TO_BD);
            try {
                connection.setAutoCommit(true);
                connection.rollback();
            } catch (SQLException e1) {
                log.error(CAN_NOT_DO_ROLLBACK + e1);
            }
        }
    }

    @Override
    public List<Request> getRequestsSortedByQuantity() {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(REQUESTS_SORTED_BY_QUANTITY);
        try {
            return requestDao.getSortedByQuantity(connection);
        } catch (SQLException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Request> getRequestsSortedByAlphabet() {
        Connection connection = ConnectionDB.getConnection();
        notifyObservers(REQUESTS_SORTED_BY_ALPHABET);
        try {
            return requestDao.getSortedByAlphabet(connection);
        } catch (SQLException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Request> getAll() {
        Connection connection = ConnectionDB.getConnection();
        try {
            return requestDao.getAll(connection);
        }catch (SQLException e){
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Request> getCompletedRequests() {
        Connection connection = ConnectionDB.getConnection();
        try {
            return requestDao.getCompleted(connection);
        } catch (SQLException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Request> getNotCompletedRequests() {
        Connection connection = ConnectionDB.getConnection();
        try {
            return requestDao.getNotCompleted(connection);
        } catch (SQLException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void exportToCsv() {
        Connection connection = ConnectionDB.getConnection();
        try {
            super.writeToCsv(requestDao.getAll(connection));
        } catch (SQLException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() {
        List<Request> importListFromFile = fileWorker.importListFromFile(PATH_REQUEST_CSV, Request.class);
        notifyObservers(PATH_REQUEST_CSV);
        merge(importListFromFile, requestDao);
    }
}
