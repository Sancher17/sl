package com.senla.services.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.imports.IImportFromCsv;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.repositories.IRepositoryRequest;
import com.senla.services.IServiceRequest;
import com.senla.util.comparators.request.ComparatorRequestsByAlphabet;
import com.senla.util.comparators.request.ComparatorRequestsByQuantity;
import com.senla.util.dataworker.IDataWorker;
import entities.Request;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.PATH_FOR_CSV;
import static com.senla.mainmodule.constants.Constants.PATH_REQUEST_CSV;


public class ServiceRequest extends Service implements IServiceRequest {

    private static final Logger log = Logger.getLogger(ServiceRequest.class);
    private IRepositoryRequest repositoryRequest;
    private IDataWorker dataWorker;
    private com.senla.fileworker.startModule.IFileWorker fileWorker;

    public ServiceRequest(IRepositoryRequest repositoryRequest) {
        this.repositoryRequest = repositoryRequest;
        this.dataWorker = DependencyInjection.getBean(IDataWorker.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addBookRequest(Request request) {
        notifyObservers("Добавлен запрос на книгу: " + request.getRequireNameBook());
        boolean exist = false;
        for (Request aRequest : repositoryRequest.getAll()) {
            if (aRequest != null) {
                if (aRequest.getRequireNameBook().equals(request.getRequireNameBook())) {
                    exist = true;
                    aRequest.setRequireQuantity(aRequest.getRequireQuantity() + 1);
                }
            }
        }
        if (!exist) {
            request.setRequireQuantity(1);
            repositoryRequest.add(request);
        }
    }

    @Override
    public List<Request> sortRequestsByQuantity() {
        notifyObservers("Запросы отсортированы по количеству запросов");
        List<Request> sortedList = repositoryRequest.getAll();
        sortedList.sort(new ComparatorRequestsByQuantity());
        return sortedList;
    }

    @Override
    public List<Request> sortRequestsByAlphabet() {
        notifyObservers("Запросы отсортированы по алфавиту");
        List<Request> sortedList = repositoryRequest.getAll();
        sortedList.sort(new ComparatorRequestsByAlphabet());
        return sortedList;
    }

    @Override
    public List<Request> getAll() {
        return repositoryRequest.getAll();
    }

    @Override
    public List<Request> getCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Request request : repositoryRequest.getAll()) {
            if (request.getRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    @Override
    public List<Request> getNotCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Request request : repositoryRequest.getAll()) {
            if (!request.getRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    @Override
    public void exportToCsv() {
        super.writeToCsv(repositoryRequest.getAll());
    }

    @Override
    public void importFromCsv() {
        List<Request> importListFromFile = fileWorker.importListFromFile(PATH_REQUEST_CSV, Request.class);
        merge(importListFromFile, repositoryRequest);
    }

    @Override
    public void readDataFromFile(String path) {
        repositoryRequest.getAll().clear();
        repositoryRequest.setAll(dataWorker.readDataFromFile(path));
    }

    @Override
    public void writeDataToFile() {
        dataWorker.writeDataToFile(repositoryRequest.getAll());
    }
}
