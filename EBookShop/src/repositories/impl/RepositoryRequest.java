package repositories.impl;

import entities.Request;
import repositories.IRepositoryRequest;
import util.ID;

import java.util.ArrayList;
import java.util.List;

public class RepositoryRequest implements IRepositoryRequest {

    private Long lastId = 0L;
    private List<Request> requests = new ArrayList<>();
    private static RepositoryRequest instance = null;

    public static RepositoryRequest getInstance() {
        if (instance == null) {
            instance = new RepositoryRequest();
        }
        return instance;
    }

    private RepositoryRequest() {
    }

    @Override
    public void add(Request request) {
        lastId = findMaxId();
        lastId = ID.nextId(lastId);
        request.setId(lastId);
        requests.add(request);
    }

    private long findMaxId(){
        long id = 0;
        for (Request request: requests){
            if (request.getId() > id){
                id = request.getId();
            }
        }
        return id;
    }

    @Override
    public void deleteById(int id) {
        requests.removeIf(request -> request.getId() == id);
    }

    @Override
    public void deleteAll(List list) {
        list.clear();
    }

    @Override
    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public Request getById(int id) {
        for(Request request: requests){
            if(request.getId() == id){
                return request;
            }
        }
        return null;
    }

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }
}
