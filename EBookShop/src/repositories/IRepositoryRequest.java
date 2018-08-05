package repositories;

import entities.Book;
import entities.Request;

import java.util.List;

public interface IRepositoryRequest {

    void add(Request request);

    Request getById(int id);

    void deleteById(int id);

    void deleteAll(List list);

    List<Request> getRequests();

    void setRequests(List<Request> requests);

    void setLastId(Long lastId);
}
