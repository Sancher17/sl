package Zanyatie4.Task1.repository;

import Zanyatie4.Task1.entity.Book;
import Zanyatie4.Task1.entity.Request;

import java.util.*;

public class BookRepository extends Repository{

    private Book[] books = new Book[1];

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookRepository{" +
                "books=" + Arrays.toString(books) +
                '}';
    }

}
