package com.senla.mainmodule.services.impl;

import com.senla.fileworker.imports.ImportRequestFromCsv;
import com.senla.mainmodule.repositories.IRepositoryRequest;
import com.senla.mainmodule.services.IServiceRequest;
import com.senla.mainmodule.util.comparators.request.ComparatorRequestsByAlphabet;
import com.senla.mainmodule.util.comparators.request.ComparatorRequestsByQuantity;
import com.senla.mainmodule.util.dataworker.DataWorker;
import com.senla.mainmodule.util.dataworker.IDataWorker;
import com.senla.fileworker.imports.mergeimport.Merger;
import com.senla.fileworker.imports.mergeimport.MergerRequest;
import entities.Request;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.PATH_REQUEST_CSV;

public class ServiceRequest extends Service implements IServiceRequest {

    private static final Logger log = Logger.getLogger(ServiceRequest.class);
    private IRepositoryRequest repositoryRequest;
    private IDataWorker fileWorker = new DataWorker();


    public ServiceRequest(IRepositoryRequest repositoryRequest) {
        this.repositoryRequest = repositoryRequest;
    }

    @Override
    public void addBookRequest(String nameRequireBook) {
        Request newRequest = new Request(nameRequireBook);
        notifyObservers("Добавлен запрос на книгу: " + newRequest.getRequireNameBook());
        boolean exist = false;
        for (Object obj : repositoryRequest.getAll()) {
            Request request = (Request) obj;
            if (request != null) {
                if (request.getRequireNameBook().equals(nameRequireBook)) {
                    exist = true;
                    request.setRequireQuantity(request.getRequireQuantity() + 1);
                }
            }
        }
        if (!exist) {
            newRequest.setRequireQuantity(1);
            repositoryRequest.add(newRequest);
        }
    }

    @Override
    public void sortRequestsByQuantity() {
        repositoryRequest.getAll().sort(new ComparatorRequestsByQuantity());
        notifyObservers("Запросы отсортированы по количеству запросов");
    }

    @Override
    public void sortRequestsByAlphabet() {
        repositoryRequest.getAll().sort(new ComparatorRequestsByAlphabet());
        notifyObservers("Запросы отсортированы по алфавиту");
    }

    @Override
    public List<Request> getAll() {
        return repositoryRequest.getAll();
    }


    @Override
    public List<Request> getCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Object obj : repositoryRequest.getAll()) {
            Request request = (Request) obj;
            if (request.getRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    @Override
    public List<Request> getNotCompletedRequests() {
        List<Request> requestList = new ArrayList<>();
        for (Object obj : repositoryRequest.getAll()) {
            Request request = (Request) obj;
            if (!request.getRequireIsCompleted()) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    @Override
    public void setLastId() {
        Long id = 0L;
        for (Object obj : repositoryRequest.getAll()) {
            Request request = (Request) obj;
            if (request.getId() > id) {
                id = request.getId();
            }
        }
        repositoryRequest.setLastId(id);
    }


    @Override
    public void exportToCsv() {
        super.writeToCsv(repositoryRequest.getAll());
    }

    public void importFromCsv(){
        ImportRequestFromCsv importList = new ImportRequestFromCsv();
        List<Request> temp = importList.importListFromFile(PATH_REQUEST_CSV);
        Merger<Request> merger = new MergerRequest(repositoryRequest.getAll());
        repositoryRequest.setAll(merger.merge(temp));
    }

    @Override
    public void readDataFromFile(String path) {
        repositoryRequest.getAll().clear();
        repositoryRequest.setAll(fileWorker.readDataFromFile(path));
    }

    @Override
    public void writeDataToFile() {
        fileWorker.writeDataToFile(this, repositoryRequest.getAll());
    }
}
