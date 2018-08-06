package com.senla.mainmodule.util.comparators.book;

import com.senla.mainmodule.entities.Book;

import java.util.Comparator;

public class ComparatorBookByDatePublication implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getDateOfPublication().compareTo(o2.getDateOfPublication());
    }
}
