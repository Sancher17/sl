package com.senla.hibernate;

import entities.Request;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface IRequestDao extends GenericDAO<Request> {

    List<Request> getSortedByQuantity(Session session);

    List<Request> getSortedByAlphabet(Session session);

    List<Request> getCompleted(Session session);

    List<Request> getNotCompleted(Session session);
}
