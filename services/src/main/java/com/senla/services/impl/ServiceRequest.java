package com.senla.services.impl;

import com.senla.db.IRequestDao;
import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.repositories.IRepositoryRequest;
import com.senla.services.IServiceRequest;
import com.senla.util.comparators.request.ComparatorRequestsByAlphabet;
import com.senla.util.comparators.request.ComparatorRequestsByQuantity;
import entities.Request;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.PATH_REQUEST_CSV;


public class ServiceRequest extends Service implements IServiceRequest {

    private static final Logger log = Logger.getLogger(ServiceRequest.class);
    private IRequestDao requestDao;
    private IFileWorker fileWorker;

    public ServiceRequest() {
        this.requestDao = DependencyInjection.getBean(IRequestDao.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addBookRequest(Request request) {
        notifyObservers("Добавлен запрос на книгу: " + request.getRequireNameBook());
        boolean exist = false;
        for (Request aRequest : requestDao.getAll()) {
            if (aRequest != null) {
                if (aRequest.getRequireNameBook().equals(request.getRequireNameBook())) {
                    exist = true;
                    aRequest.setRequireQuantity(aRequest.getRequireQuantity() + 1);
                }
            }
        }
        if (!exist) {
            request.setRequireQuantity(1);
            requestDao.add(request);
        }
    }

    @Override
    public List<Request> sortRequestsByQuantity() {
        notifyObservers("Запросы отсортированы по количеству запросов");
        List<Request> sortedList = requestDao.getAll();
        sortedList.sort(new ComparatorRequestsByQuantity());
        return sortedList;
    }

    @Override
    public List<Request> sortRequestsByAlphabet() {
        notifyObservers("Запросы отсортированы по алфавиту");
        List<Request> sortedList = requestDao.getAll();
        sortedList.sort(new ComparatorRequestsByAlphabet());
        return sortedList;
    }

    @Override
    public List<Request> getAll() {
        return requestDao.getAll();
    }

    @Override
    public List<Request> getCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Request request : requestDao.getAll()) {
            if (request.getRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    @Override
    public List<Request> getNotCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Request request : requestDao.getAll()) {
            if (!request.getRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    @Override
    public void exportToCsv() {
        super.writeToCsv(requestDao.getAll());
    }

    @Override
    public void importFromCsv() {
//        List<Request> importListFromFile = fileWorker.importListFromFile(PATH_REQUEST_CSV, Request.class);
//        notifyObservers(PATH_REQUEST_CSV);
//        merge(importListFromFile, requestDao);
    }
}
