package com.senla.mainmodule.repositories.impl;


import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.repositories.IRepository;
import com.senla.mainmodule.repositories.util.Id;

import java.util.ArrayList;
import java.util.List;

public class RepositoryRequest implements IRepository {

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
    public void add(Object obj) {
        lastId = Id.nextId(lastId);
        Request request = (Request) obj;
        request.setId(lastId);
        requests.add(request);
    }

    @Override
    public void deleteById(Long id) {
        requests.removeIf(order -> order.getId().equals(id));
    }

    @Override
    public Object getById(Long id) {
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
    public void setAll(List requests) {
        this.requests = requests;
    }

    @Override
    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

}
