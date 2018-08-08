package com.senla.mainmodule.util.fileworker.csvworker.imports;

import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.impl.ServiceOrder;
import com.senla.mainmodule.services.impl.ServiceRequest;
import com.senla.mainmodule.util.fileworker.csvworker.merger.Merger;
import com.senla.mainmodule.util.fileworker.csvworker.merger.MergerOrder;
import com.senla.mainmodule.util.fileworker.csvworker.merger.MergerRequest;

import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.PATH_REQUEST_CSV;
import static com.senla.mainmodule.util.fileworker.csvworker.parser.Parse.parseBoolean;
import static com.senla.mainmodule.util.fileworker.csvworker.parser.Parse.parseInteger;
import static com.senla.mainmodule.util.fileworker.csvworker.parser.Parse.parseLong;

public class ImportRequestFromCsv extends ImportCsv {

    private List<Request> tempRequests = new ArrayList<>();

    @Override
    public void runImport(String path) {
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
        IService service = ServiceRequest.getInstance();
        Merger merger = new MergerRequest(service);
        service.setRepo(merger.merge(list));
    }
}
