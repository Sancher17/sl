package repositories;

import entities.Request;
import util.ID;

import java.util.ArrayList;
import java.util.List;

public class RepositoryRequest implements IRepository {

    private Long id = 0L;
    private List<Request> requests = new ArrayList<>();

    @Override
    public void add(Object obj) {
        id = findMaxId();
        id = ID.nextId(id);
        Request request = (Request) obj;
        request.setId(id);
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
    public Object getById(int id) {
        for(Request request: requests){
            if(request.getId() == id){
                return request;
            }
        }
        return null;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "RequestRepository{" +
                "requests=" + requests +
                '}';
    }
}
