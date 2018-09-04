package com.senla.mainmodule.util.comparators.request;

import entities.Request;

import java.util.Comparator;

public class ComparatorRequestsByQuantity implements Comparator<Request> {

    @Override
    public int compare(Request o1, Request o2) {
        if (o1 == o2) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        } else {
            return Integer.compare(o1.getRequireQuantity(), o2.getRequireQuantity());
        }
    }
}
