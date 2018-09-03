package com.senla.mainmodule.util.fileworker.csvworker.exports;

import com.senla.mainmodule.services.IService;
import entities.Request;

import java.util.List;

public class ExportRequestToCsv extends Export {

    private IService service;

    public ExportRequestToCsv(IService service) {
        this.service = service;
    }

    public String prepareData(){
        List<Request> requests = service.getRepo();
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
}















