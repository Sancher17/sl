package com.senla.services;

import entities.Request;

import java.util.List;

public interface IServiceRequest extends IService <Request>{

    void addBookRequest(String nameRequireBook);

    List<Request> sortRequestsByQuantity();

    List<Request> sortRequestsByAlphabet();

    List<Request> getAll();

    List<Request> getCompletedRequests();

    List<Request> getNotCompletedRequests();
}
