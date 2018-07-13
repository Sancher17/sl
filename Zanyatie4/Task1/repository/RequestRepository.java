package Zanyatie4.Task1.repository;

import Zanyatie4.Task1.entity.Request;

import java.util.Arrays;

public class RequestRepository {

    private Request[] requests = new Request[1];

    public void increaseArray() {
        int count = requests.length - checkNullRow();
        if (requests.length - count < 3) {
            requests = Arrays.copyOf(requests, requests.length * 2);
        }
    }

    private int checkNullRow() {
        int count = 0;
        for (Request request : requests) {
            if (request != null) {
                count++;
            }
        }
        return count;
    }

    public Request[] getRequests() {
        return requests;
    }

    public void setRequests(Request[] requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "RequestRepository{" +
                "requests=" + Arrays.toString(requests) +
                '}';
    }
}
