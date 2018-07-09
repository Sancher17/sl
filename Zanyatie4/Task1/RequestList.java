package Zanyatie4.Task1;

import Zanyatie4.Task1.entity.Request;

import java.util.Arrays;
import java.util.Comparator;

public class RequestList {

    private Request[] requests = new Request[4];

    // TODO: 09.07.2018 исправить
    public void addBookRequest(Request request) {
        int count = 0;
        for (Request aRequest : requests) {
            if (aRequest != null) {
                count++;
            }
        }
        if (requests.length - count < 3) {
            checkSizeOfArray(requests);
        }
        int index = checkNullRow(requests);

        if (requests[0] == null) {
            requests[index] = request;
            requests[index].setRequireQuantity(1);
        } else {
            for (int i = 0; i < requests.length; i++) {
                if (requests[i] != null) {
                    String nameOfRequire = request.getRequireNameBook();
                    String nameFromList = requests[i].getRequireNameBook();
                    if (nameOfRequire.equals(nameFromList)) {
                        requests[i].setRequireQuantity(quantity(request));
                    } else {
                        requests[index] = request;

                    }
                }
            }
            deleteCopy();
        }
    }

    // TODO: 09.07.2018 исправить вместе с addBookRequest ()
    private void deleteCopy() {
        int index = checkNullRow(requests);
        for (int i = 0; i < index-1; i++) {
            if (requests != null){
                if (requests[i].getRequireNameBook().equals(requests[i+1].getRequireNameBook())){
                    deleteRequestById(i);
//                    i--;
                }
            }
        }
    }

    public void printRequests() {
        for (Request aRequest : requests) {
            if (aRequest != null) {
                System.out.println(aRequest);
            }
        }
    }

    public void printCompletedRequests(Request[] requests) {
        for (Request aRequest : requests) {
            if (aRequest != null) {
                if (aRequest.isRequireIsCompleted()) {
                    System.out.println(aRequest);
                }
            }
        }
    }

    public void printNotCompletedRequests(Request[] requests) {
        for (Request aRequest : requests) {
            if (aRequest != null) {
                if (!aRequest.isRequireIsCompleted()) {
                    System.out.println(aRequest);
                }
            }
        }
    }

    public void sortRequestsByQuantity() {
        Comparator<Request> comp = (o1, o2) -> {
            int quantityFirst = 0;
            int quantitySecond = 0;
            for (Request request : requests) {
                if (request != null) {
                    String tempName = request.getRequireNameBook();
                    for (Request requestTemp : requests) {
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
        Arrays.sort(requests, comp);

    }

    public void sortRequestsByAlphabet() {
        Comparator<Request> requestComp = Comparator.comparing(Request::getRequireNameBook);
        Comparator<Request> requestComp_nullLast = Comparator.nullsLast(requestComp);
        Arrays.sort(requests, requestComp_nullLast);
    }

    private void deleteRequestById(int id) {
        System.arraycopy(requests, id + 1, requests, id, requests.length - 1 - id);
    }

    private int quantity(Request request) {
        int quantityOfCoincide = 1;
        for (Request aRequest : requests) {
            if (aRequest != null) {
                if (aRequest.getRequireNameBook() == request.getRequireNameBook()) {
                    quantityOfCoincide++;
                }
            }
        }
        return quantityOfCoincide;
    }

    private int checkNullRow(Object[] obj) {
        int count = 0;
        for (Object anObj : obj) {
            if (anObj != null) {
                count++;
            }
        }
        return count;
    }

    private <T> void checkSizeOfArray(T[] array) {
        int count = 0;
        for (Object obj : array) {
            if (obj != null) {
                count++;
            }
        }
        if (array.length - count < 3) {
            increaseSizeOfArray(requests);
        }
    }

    private <T> void increaseSizeOfArray(T[] array) {
        int increase = 5;
        if (array == requests) {
            requests = Arrays.copyOf(requests, requests.length + increase);
        }
    }


    //getters setters
    public Request[] getRequests() {
        return requests;
    }

    public void setRequests(Request[] requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "RequestList{" +
                "requests=" + Arrays.toString(requests) +
                '}';
    }
}
