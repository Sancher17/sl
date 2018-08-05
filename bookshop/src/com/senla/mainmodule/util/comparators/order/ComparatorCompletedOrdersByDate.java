package com.senla.mainmodule.util.comparators.order;

import entities.Order;

import java.util.Comparator;

public class ComparatorCompletedOrdersByDate implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getDateOfCompletedOrder() == null) {
            return (o2.getDateOfCompletedOrder() == null) ? 0 : -1;
        }
        if (o2.getDateOfCompletedOrder() == null) {
            return 1;
        }
        return o2.getDateOfCompletedOrder().compareTo(o1.getDateOfCompletedOrder());
    }
}
