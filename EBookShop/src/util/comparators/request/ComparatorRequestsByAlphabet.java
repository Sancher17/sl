package util.comparators.request;

import entities.Request;

import java.util.Comparator;

public class ComparatorRequestsByAlphabet implements Comparator<Request> {

    @Override
    public int compare(Request o1, Request o2) {
        return o1.getRequireNameBook().compareTo(o2.getRequireNameBook());
    }
}
