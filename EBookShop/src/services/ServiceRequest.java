package services;

import com.danco.training.TextFileWorker;
import data.parse.ParseRequest;
import entities.Request;
import repositories.RepositoryRequest;

import java.util.*;

import static constants.Constants.*;


public class ServiceRequest extends Observable implements Service {

    private String filePath = PATH_REQUEST_DATA + "";

    private RepositoryRequest requests = new RepositoryRequest();
    private ParseRequest parseRequest = new ParseRequest(filePath);

    private Request[] tempRequest;
    private String[] tempData;

    private List<Observer> subscribers = new ArrayList<>();

    public void writeToFile() {
        parseRequest.writeObjectToFile(requests.getRequests().toArray());
    }

    public void readFromFileFillData(String path) {
        TextFileWorker fileWorker = new TextFileWorker(path);
        tempData = fileWorker.readFromFile();
        tempRequest = new Request[tempData.length];
        for (int i = 0; i < tempData.length; i++) {
            tempRequest[i] = parseRequest.createObject(tempData[i]);
        }
        requests.deleteAll(requests.getRequests());
        List<Request> tempList = new ArrayList<>(Arrays.asList(tempRequest));
        requests.setRequests(tempList);
    }

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

    public String printRequests() {
        StringBuilder builder = new StringBuilder();
        if (requests.getRequests().size() > 0) {
            for (Request request : requests.getRequests()) {
                builder.append(request).append("\n");
            }
            return String.valueOf(builder);
        }
        return "nothing to show";
    }

    public String printCompletedRequests() {
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

    public String printNotCompletedRequests() {
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

    public void sortRequestsByQuantity() {
        Comparator<Request> requestComp = Comparator.comparing(Request::getRequireQuantity);
        Comparator<Request> requestComp_nullLast = Comparator.nullsLast(requestComp);
        requests.getRequests().sort(requestComp_nullLast);
        notifyObservers("Запросы отсортированы по количеству запросов");
    }

    public void sortRequestsByAlphabet() {
        Comparator<Request> requestComp = Comparator.comparing(Request::getRequireNameBook);
        Comparator<Request> requestComp_nullLast = Comparator.nullsLast(requestComp);
        requests.getRequests().sort(requestComp_nullLast);
        notifyObservers("Запросы отсортированы по алфавиту");
    }


    public RepositoryRequest getRequests() {
        return requests;
    }


    @Override
    public synchronized void addObserver(Observer o) {
        subscribers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        subscribers.remove(o);
    }


    @Override
    public void notifyObservers(Object arg) {
        for (Observer observer : subscribers) {
            System.out.println(arg);
        }
    }
}
