package com.senla.mainmodule.services;

import entities.Request;

import java.util.List;

public interface IServiceRequest extends IService {

    void addBookRequest(String nameRequireBook);

    void sortRequestsByQuantity();

    void sortRequestsByAlphabet();

    List<Request> getAll();

    List<Request> getCompletedRequests();

    List<Request> getNotCompletedRequests();
}
