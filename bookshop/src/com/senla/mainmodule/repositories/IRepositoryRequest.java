package com.senla.mainmodule.repositories;



import com.senla.mainmodule.entities.Request;

import java.util.List;

public interface IRepositoryRequest {

    void add(Request request);

    List<Request> getRequests();

    void setRequests(List<Request> requests);

    void setLastId(Long lastId);
}
