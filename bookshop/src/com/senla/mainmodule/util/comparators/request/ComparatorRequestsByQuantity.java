package com.senla.mainmodule.util.comparators.request;

import com.senla.mainmodule.entities.Request;

import java.util.Comparator;

public class ComparatorRequestsByQuantity implements Comparator<Request> {

    @Override
    public int compare(Request o1, Request o2) {
        return Integer.compare(o1.getRequireQuantity(), o2.getRequireQuantity());
    }
}
