package com.senla.repositories;

import entities.Request;

import java.util.List;

public interface IRepositoryRequest extends IRepository<Request> {

    void setAll(List<Request> entity);

}
