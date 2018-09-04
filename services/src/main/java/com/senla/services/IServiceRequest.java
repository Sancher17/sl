package com.senla.services;

import entities.Request;

import java.util.List;

public interface IServiceRequest extends IService <Request>{

    void addBookRequest(String nameRequireBook);

    void sortRequestsByQuantity();

    void sortRequestsByAlphabet();

    List<Request> getAll();

    List<Request> getCompletedRequests();

    List<Request> getNotCompletedRequests();
}
