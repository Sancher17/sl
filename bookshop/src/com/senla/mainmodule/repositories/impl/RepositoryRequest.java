package com.senla.mainmodule.repositories.impl;


import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.repositories.IRepositoryRequest;
import com.senla.mainmodule.repositories.util.ID;

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
        lastId = ID.nextId(lastId);
        request.setId(lastId);
        requests.add(request);
    }

    @Override
    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }
}
