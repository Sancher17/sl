package com.senla.repositories.impl;

import com.senla.repositories.IRepositoryRequest;
import com.senla.repositories.util.Id;
import entities.Book;
import entities.Request;

import java.util.ArrayList;
import java.util.List;

public class RepositoryRequest implements IRepositoryRequest {

    private static Long lastId = 0L;
    private List<Request> requests = new ArrayList<>();
    private static RepositoryRequest instance = null;
    public static RepositoryRequest getInstance() {
        if (instance == null) {
            instance = new RepositoryRequest();
        }
        return instance;
    }

    private RepositoryRequest() {
    }

    @Override
    public void add(Request request) {
        lastId = findMaxId();
        lastId = Id.nextId(lastId);
        request.setId(lastId);
        requests.add(request);
    }

    private Long findMaxId() {
        Long maxId = 0L;
        for (Request request :  requests) {
            if (request.getId() > maxId) {
                maxId = request.getId();
            }
        }
        return maxId;
    }

    @Override
    public void deleteById(Long id) {
        requests.removeIf(order -> order.getId().equals(id));
    }

    @Override
    public Request getById(Long id) {
        for(Request request: requests){
            if(request.getId().equals(id)){
                return request;
            }
        }
        return null;
    }

    @Override
    public void update(Request entity) {
        int index;
        Long id = entity.getId();
        for (Request newBook : requests) {
            if (newBook.getId().equals(id)) {
                index = requests.indexOf(newBook);
                requests.set(index, newBook);
            }
        }
    }

    @Override
    public List<Request> getAll() {
        return requests;
    }

    @Override
    public void addAll(List<Request> notExistList) {
        requests.addAll(notExistList);
    }

    @Override
    public void setAll(List<Request> requests) {
        this.requests = requests;
    }
}
