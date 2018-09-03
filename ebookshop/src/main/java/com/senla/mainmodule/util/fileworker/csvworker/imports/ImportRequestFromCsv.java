package com.senla.mainmodule.util.fileworker.csvworker.imports;

import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.util.fileworker.csvworker.merger.Merger;
import com.senla.mainmodule.util.fileworker.csvworker.merger.MergerRequest;
import entities.Request;

import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.util.fileworker.csvworker.parser.Parse.*;

public class ImportRequestFromCsv extends ImportCsv {

    private IService service;
    private List<Request> tempRequests = new ArrayList<>();

    public ImportRequestFromCsv(IService service) {
        this.service = service;
    }

    @Override
    public void importFromFile(String path) {
        read(path);
        createObjectList();
        writeToRepository(tempRequests);
    }

    @Override
    public void createObjectList() {
        List<String> list = getTempDataString();
        for (String str : list) {
            String[] temp = str.split(";");
            String id = temp[0];
            String requireNameBook = temp[1];
            String requireIsCompleted = temp[2];
            String requireQuantity = temp[3];

            Request request = new Request();
            request.setId(parseLong(id));
            request.setRequireNameBook(requireNameBook);
            request.setRequireIsCompleted(parseBoolean(requireIsCompleted));
            request.setRequireQuantity(parseInteger(requireQuantity));

            tempRequests.add(request);
        }
    }

    private void writeToRepository(List<Request> list) {
        Merger merger = new MergerRequest(service);
        service.setRepo(merger.merge(list));
    }
}
