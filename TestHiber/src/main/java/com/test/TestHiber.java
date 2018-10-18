package com.test;

import com.senla.services.IServiceOrder;
import com.senla.services.impl.ServiceOrder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestHiber {

    public static void main(String[] args) {

//        IServiceBook serviceBook = new ServiceBook();
//        printList(serviceBook.getAll());

        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY,1);
        Date start = cal.getTime();
        cal.set(2018,Calendar.OCTOBER,1);
        Date dateEnd = cal.getTime();

        IServiceOrder serviceOrder = new ServiceOrder();
//        printList(serviceOrder.getCompletedOrdersSortedByDate());
//        printList(serviceOrder.getAll());
        printList(serviceOrder.getCompletedOrdersSortedByPriceOfPeriod(start, dateEnd));


    }


    @SuppressWarnings("unchecked")
    private static void printList(List list) {
        list.forEach(System.out::println);
    }
}
