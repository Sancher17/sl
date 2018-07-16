package Zanyatie4.Task1.service;

import Zanyatie4.Task1.data.ParseRequest;
import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.entity.Request;
import Zanyatie4.Task1.repository.RequestRepository;
import com.danco.training.TextFileWorker;

import java.util.Arrays;
import java.util.Comparator;

import static Zanyatie4.Task1.constants.Constants.PATH_REQUEST_DATA;

public class RequestService {

    private String filePath = PATH_REQUEST_DATA + "";

    private RequestRepository requests = new RequestRepository();
    private ParseRequest parseRequest = new ParseRequest(filePath);

    private Request[] tempRequest;
    private String[] tempData;


    public void writeRequestToFile() {
        parseRequest.writeObjectToFile(requests.getRequests());
    }

    public void readRequestFromFileFillData(String path) {
        TextFileWorker fileWorker = new TextFileWorker(path);
        tempData = fileWorker.readFromFile();
        tempRequest = new Request[tempData.length];
        for (int i = 0; i < tempData.length; i++) {
            tempRequest[i] = parseRequest.createObject(tempData[i]);
        }
        requests.deleteAll(requests.getRequests());
        requests.setRequests(tempRequest);
    }

    public void addBookRequest(String nameRequireBook) {
        requests.setRequests((Request[]) requests.increaseArray(requests.getRequests()));
        int index = checkNullRow();

        if (requests.getRequests()[0] == null) {
            requests.getRequests()[index] = new Request(nameRequireBook);
            requests.getRequests()[index].setRequireQuantity(1);
        } else {
            for (int i = 0; i < requests.getRequests().length; i++) {
                if (requests.getRequests()[i] != null) {
                    String nameFromList = requests.getRequests()[i].getRequireNameBook();
                    if (nameRequireBook.equals(nameFromList)) {
                        requests.getRequests()[i].setRequireQuantity(1);
                    } else {
                        requests.getRequests()[index] = new Request(nameRequireBook);
                    }
                }
            }
            deleteCopy();
        }
    }

    private void deleteCopy() {
        int index = checkNullRow();
        for (int i = 0; i < index - 1; i++) {
            if (requests != null) {
                if (requests.getRequests()[i].getRequireNameBook().equals(requests.getRequests()[i + 1].getRequireNameBook())) {
                    deleteRequestById(i);
//                    i--;
                }
            }
        }
    }

    public String printRequests() {
        StringBuilder builder = new StringBuilder();
        if (requests.getRequests()[0] != null) {
            for (Request request : requests.getRequests()) {
                if (request != null && requests.getRequests()[0] != null) {
                    builder.append(request + "\n");
                }
            }
            return String.valueOf(builder);
        }
        return "nothing to show";
    }

    public String printCompletedRequests() {
        StringBuilder builder = new StringBuilder();
        for (Request request : requests.getRequests()) {
            if (request != null) {
                if (request.isRequireIsCompleted()) {
                    builder.append(request + "\n");
                }
            }
        }
        return String.valueOf(builder);
    }

    public String printNotCompletedRequests() {
        StringBuilder builder = new StringBuilder();
        for (Request request : requests.getRequests()) {
            if (request != null) {
                if (!request.isRequireIsCompleted()) {
                    builder.append(request + "\n");
                }
            }
        }
        return String.valueOf(builder);
    }

    public void sortRequestsByQuantity() {
        Comparator<Request> comp = (o1, o2) -> {
            int quantityFirst = 0;
            int quantitySecond = 0;
            for (Request request : requests.getRequests()) {
                if (request != null) {
                    String tempName = request.getRequireNameBook();
                    for (Request requestTemp : requests.getRequests()) {
                        if (requestTemp != null) {
                            if (tempName == requestTemp.getRequireNameBook()) {
                                quantityFirst++;
                            }
                        }
                        quantitySecond++;
                    }
                }
            }

//            String[] str = o1.split(" ");
//            int quantityFirst = str.length;
//            String[] str2 = o2.split(" ");
//            int quantitySecond = str2.length;
//
            return Integer.compare(quantityFirst, quantitySecond);
        };
        Arrays.sort(requests.getRequests(), comp);

    }

    public void sortRequestsByAlphabet() {
        Comparator<Request> requestComp = Comparator.comparing(Request::getRequireNameBook);
        Comparator<Request> requestComp_nullLast = Comparator.nullsLast(requestComp);
        Arrays.sort(requests.getRequests(), requestComp_nullLast);
    }

    private void deleteRequestById(int id) {
        System.arraycopy(requests, id + 1, requests, id, requests.getRequests().length - 1 - id);
    }

    // TODO: 15.07.2018
//    private int quantityOfCoincide(String nameRequireBook) {
////        int count = 1;
////        for (Request request: requests.getRequests()){
////            if (requests.getRequests() != null){
////                if (request.getRequireNameBook().equals(nameRequireBook)){
////                    count++;
////                }
////            }
////        }
////        return count;
//    }

    private int checkNullRow() {
        int count = 0;
        for (Request request : requests.getRequests()) {
            if (request != null) {
                count++;
            }
        }
        return count;
    }

    public RequestRepository getRequests() {
        return requests;
    }

    public void setRequests(RequestRepository requests) {
        this.requests = requests;
    }
}
