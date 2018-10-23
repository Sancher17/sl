package com.senla.api.dao;

import entities.Request;
import org.hibernate.Session;

import java.util.List;


public interface IRequestDao extends IGenericDao<Request> {

    List<Request> getSortedByQuantity(Session session);

    List<Request> getSortedByAlphabet(Session session);

    List<Request> getCompleted(Session session);

    List<Request> getNotCompleted(Session session);
}
