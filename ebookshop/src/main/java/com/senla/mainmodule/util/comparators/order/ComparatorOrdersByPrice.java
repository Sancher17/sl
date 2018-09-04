package com.senla.mainmodule.util.comparators.order;


import entities.Order;

import java.util.Comparator;

public class ComparatorOrdersByPrice implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if (o1 == o2) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        } else{
            return Double.compare(o1.getBook().getPrice(), o2.getBook().getPrice());
        }
    }
}
