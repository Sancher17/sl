package com.senla.repositories.impl;

import com.senla.repositories.IRepositoryRequest;
import com.senla.repositories.util.Id;
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
        lastId = Id.nextId(lastId);
        request.setId(lastId);
        requests.add(request);
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
    public List<Request> getAll() {
        return requests;
    }

    @Override
    public void setAll(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

}
