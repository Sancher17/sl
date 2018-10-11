package com.senla.services;

import entities.Request;

import java.util.List;

public interface IServiceRequest extends IService <Request>{

    void addBookRequest(Request request);

    List<Request> getRequestsSortedByQuantity();

    List<Request> getRequestsSortedByAlphabet();

    List<Request> getAll();

    List<Request> getCompletedRequests();

    List<Request> getNotCompletedRequests();
}
