package com.senla.mainmodule.util.comparators.order;


import com.senla.mainmodule.entities.Order;

import java.util.Comparator;

public class ComparatorOrdersByState implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return Boolean.compare(o1.isCompletedOrder(), o2.isCompletedOrder());
    }
}
