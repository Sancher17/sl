package com.senla.mainmodule.util.comparators.book;

import entities.Book;

import java.util.Comparator;

public class ComparatorBookByAvailability implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        return Boolean.compare(o1.getAvailable(), o2.getAvailable());
    }
}
