package com.senla.mainmodule.util.fileworker.csvworker.exports;

import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.impl.ServiceRequest;

import java.util.List;

public class ExportRequestToCsv extends Export {

    public String prepareData(){
        List<Request> requests = readFromRepository();
        StringBuilder sb = new StringBuilder();
        for (Request request : requests) {
            sb.append(request.getId()).append(";");
            sb.append(request.getRequireNameBook()).append(";");
            sb.append(request.getRequireIsCompleted()).append(";");
            sb.append(request.getRequireQuantity()).append(";");
            sb.append("\n");
        }
        return sb.toString();
    }

    private List<Request> readFromRepository(){
        IService service = ServiceRequest.getInstance();
        return service.getRepo();
    }
}















