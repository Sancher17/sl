package com.senla.util.comparators.book;


import entities.Book;

import java.util.Comparator;

public class ComparatorBookByPrice implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        if (o1 == o2) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        } else {
            return Double.compare(o1.getPrice(), o2.getPrice());
        }
    }
}
