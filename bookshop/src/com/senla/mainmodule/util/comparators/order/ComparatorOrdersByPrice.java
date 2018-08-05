package com.senla.mainmodule.util.comparators.order;

import entities.Order;

import java.util.Comparator;

public class ComparatorOrdersByPrice implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return Double.compare(o1.getBook().getPrice(), o2.getBook().getPrice());
    }
}
