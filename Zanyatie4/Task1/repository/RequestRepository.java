package Zanyatie4.Task1.repository;

import Zanyatie4.Task1.entity.Request;

import java.util.Arrays;

public class RequestRepository extends Repository {

    private Request[] requests = new Request[1];

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
