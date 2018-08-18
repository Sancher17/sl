package com.senla.mainmodule.services.impl;

import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.repositories.IRepository;
import com.senla.mainmodule.repositories.impl.RepositoryRequest;
import com.senla.mainmodule.services.IServiceRequest;
import com.senla.mainmodule.util.comparators.request.ComparatorRequestsByAlphabet;
import com.senla.mainmodule.util.comparators.request.ComparatorRequestsByQuantity;

import java.util.ArrayList;
import java.util.List;

public class ServiceRequest extends Service implements IServiceRequest {

    private IRepository requests = RepositoryRequest.getInstance();

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
        for (Object obj : requests.getAll()) {
            Request request = (Request) obj;
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
        requests.getAll().sort(new ComparatorRequestsByQuantity());
        notifyObservers("Запросы отсортированы по количеству запросов");
    }

    @Override
    public void sortRequestsByAlphabet() {
        requests.getAll().sort(new ComparatorRequestsByAlphabet());
        notifyObservers("Запросы отсортированы по алфавиту");
    }

    @Override
    public List<Request> getAll() {
        return requests.getAll();
    }


    @Override
    public List<Request> getCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Object obj : requests.getAll()) {
            Request request = (Request) obj;
            if (request.getRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    @Override
    public List<Request> getNotCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Object obj : requests.getAll()) {
            Request request = (Request) obj;
            if (!request.getRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }


    public IRepository getRepositoryRequests() {
        return requests;
    }

    @Override
    public List<Request> getRepo() {
        return requests.getAll();
    }

    @Override
    public void setRepo(List list) {
        requests.setAll(list);
        setLastId();
    }

    @Override
    public void setLastId() {
        Long id = 0L;
        for (Object obj : requests.getAll()) {
            Request request = (Request) obj;
            if (request.getId() > id) {
                id = request.getId();
            }
        }
        requests.setLastId(id);
    }
}
