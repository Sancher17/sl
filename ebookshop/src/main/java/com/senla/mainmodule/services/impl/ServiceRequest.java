package com.senla.mainmodule.services.impl;

import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.repositories.IRepositoryRequest;
import com.senla.mainmodule.repositories.impl.RepositoryRequest;
import com.senla.mainmodule.services.IServiceRequest;
import com.senla.mainmodule.util.comparators.request.ComparatorRequestsByAlphabet;
import com.senla.mainmodule.util.comparators.request.ComparatorRequestsByQuantity;

import java.util.ArrayList;
import java.util.List;

public class ServiceRequest extends Service implements IServiceRequest {

    private IRepositoryRequest requests = RepositoryRequest.getInstance();

    private static ServiceRequest instance = null;

    public static ServiceRequest getInstance() {
        if (instance == null) {
            instance = new ServiceRequest();
        }
        return instance;
    }

    private ServiceRequest() {
    }

    @Override
    public void addBookRequest(String nameRequireBook) {
        Request newRequest = new Request(nameRequireBook);
        notifyObservers("Добавлен запрос на книгу: " + newRequest.getRequireNameBook());
        boolean exist = false;
        for (Request request : requests.getRequests()) {
            if (request != null) {
                if (request.getRequireNameBook().equals(nameRequireBook)) {
                    exist = true;
                    request.setRequireQuantity(request.getRequireQuantity() + 1);
                }
            }
        }
        if (!exist) {
            newRequest.setRequireQuantity(1);
            requests.add(newRequest);
        }
    }

    @Override
    public void sortRequestsByQuantity() {
        requests.getRequests().sort(new ComparatorRequestsByQuantity());
        notifyObservers("Запросы отсортированы по количеству запросов");
    }

    @Override
    public void sortRequestsByAlphabet() {
        requests.getRequests().sort(new ComparatorRequestsByAlphabet());
        notifyObservers("Запросы отсортированы по алфавиту");
    }

    @Override
    public List<Request> getAll() {
        return requests.getRequests();
    }


    @Override
    public List<Request> getCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Request request : requests.getRequests()) {
            if (request.getRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    @Override
    public List<Request> getNotCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Request request : requests.getRequests()) {
            if (!request.getRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }


    public IRepositoryRequest getRepositoryRequests() {
        return requests;
    }

    @Override
    public List<Request> getRepo() {
        return requests.getRequests();
    }

    @Override
    public void setRepo(List list) {
        requests.setRequests(list);
        setLastId();
    }

    @Override
    public void setLastId() {
        Long id = 0L;
        for (Request request : requests.getRequests()) {
            if (request.getId() > id) {
                id = request.getId();
            }
        }
        requests.setLastId(id);
    }
}
