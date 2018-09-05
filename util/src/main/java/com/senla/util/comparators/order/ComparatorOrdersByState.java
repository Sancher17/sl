package com.senla.util.comparators.order;

import entities.Order;

import java.util.Comparator;

public class ComparatorOrdersByState implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if (o1 == o2) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        } else{
            return Boolean.compare(o1.getCompletedOrder(), o2.getCompletedOrder());
        }
    }
}
