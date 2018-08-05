package services.impl;

import entities.Request;
import repositories.IRepositoryRequest;
import repositories.impl.RepositoryRequest;
import services.IServiceRequest;
import util.comparators.request.ComparatorRequestsByAlphabet;
import util.comparators.request.ComparatorRequestsByQuantity;

import java.util.*;

public class ServiceRequest extends Service implements IServiceRequest {

    private IRepositoryRequest requests = RepositoryRequest.getInstance();

    private static ServiceRequest instance = null;

    public static ServiceRequest getInstance() {
        if (instance == null) {
            instance = new ServiceRequest();
        }
        return instance;
    }

    private ServiceRequest() {
    }

    @Override
    public void addBookRequest(String nameRequireBook) {
        Request newRequest = new Request(nameRequireBook);
        notifyObservers("Добавлен запрос на книгу: " + newRequest.getRequireNameBook());

        boolean exist = false;
        for (Request request : requests.getRequests()) {
            if (request != null) {
                if (request.getRequireNameBook().equals(nameRequireBook)) {
                    exist = true;
                    request.setRequireQuantity(request.getRequireQuantity() + 1);
                }
            }
        }
        if (!exist) {
            newRequest.setRequireQuantity(1);
            requests.add(newRequest);
        }
    }

    @Override
    public List<Request> getAll() {
        return requests.getRequests();
    }

    @Override
    public List<Request> getRequests() {
        List<Request> requestList = new ArrayList<>(requests.getRequests());
        return requestList;
    }

    @Override
    public  List<Request> getCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Request request : requests.getRequests()) {
            if (request.isRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;

    }

    @Override
    public  List<Request> getNotCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Request request : requests.getRequests()) {
            if (!request.isRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    @Override
    public void sortRequestsByQuantity() {
        requests.getRequests().sort(new ComparatorRequestsByQuantity());
        notifyObservers("Запросы отсортированы по количеству запросов");
    }

    @Override
    public void sortRequestsByAlphabet() {
        requests.getRequests().sort(new ComparatorRequestsByAlphabet());
        notifyObservers("Запросы отсортированы по алфавиту");
    }
}
