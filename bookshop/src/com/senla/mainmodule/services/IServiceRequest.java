package com.senla.mainmodule.services;

import com.senla.mainmodule.entities.Request;

import java.util.List;

public interface IServiceRequest {

    void addBookRequest(String nameRequireBook);

    void sortRequestsByQuantity();

    void sortRequestsByAlphabet();

    List<Request> getAll();

    List<Request> getRequests();

    List<Request> getCompletedRequests();

    List<Request> getNotCompletedRequests();
}
