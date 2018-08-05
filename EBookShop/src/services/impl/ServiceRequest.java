package services.impl;

import entities.Request;
import repositories.IRepositoryRequest;
import repositories.impl.RepositoryRequest;
import services.IServiceRequest;

import java.util.*;

public class ServiceRequest extends Observable implements IServiceRequest {

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

    private List<Observer> subscribers = new ArrayList<>();

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
    public String getRequests() {
        StringBuilder builder = new StringBuilder();
        if (requests.getRequests().size() > 0) {
            for (Request request : requests.getRequests()) {
                builder.append(request).append("\n");
            }
            return String.valueOf(builder);
        }
        return "nothing to show";
    }

    @Override
    public String getCompletedRequests() {
        StringBuilder builder = new StringBuilder();
        for (Request request : requests.getRequests()) {
            if (request.isRequireIsCompleted()) {
                builder.append(request).append("\n");
            }
        }
        if (builder.length() < 1) {
            return "nothing to show";
        } else {
            return String.valueOf(builder);
        }
    }

    @Override
    public String getNotCompletedRequests() {
        StringBuilder builder = new StringBuilder();
        for (Request request : requests.getRequests()) {
            if (request != null) {
                if (!request.isRequireIsCompleted()) {
                    builder.append(request).append("\n");
                }
            }
        }
        if (builder.length() < 1) {
            return "nothing to show";
        } else {
            return String.valueOf(builder);
        }
    }

    @Override
    public void sortRequestsByQuantity() {
        Comparator<Request> requestComp = Comparator.comparing(Request::getRequireQuantity);
        Comparator<Request> requestComp_nullLast = Comparator.nullsLast(requestComp);
        requests.getRequests().sort(requestComp_nullLast);
        notifyObservers("Запросы отсортированы по количеству запросов");
    }

    @Override
    public void sortRequestsByAlphabet() {
        Comparator<Request> requestComp = Comparator.comparing(Request::getRequireNameBook);
        Comparator<Request> requestComp_nullLast = Comparator.nullsLast(requestComp);
        requests.getRequests().sort(requestComp_nullLast);
        notifyObservers("Запросы отсортированы по алфавиту");
    }


//    public IRepositoryRequest getRequests() {
//        return requests;
//    }


    @Override
    public void addObserver(Observer o) {
        subscribers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        subscribers.remove(o);
    }


    @Override
    public void notifyObservers(Object arg) {
        for (Observer observer : subscribers) {
            System.out.println(arg);
        }
    }
}
