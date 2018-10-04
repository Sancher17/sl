package com.senla.db;

import entities.Book;


public interface IBookDao extends GenericDAO<Book> {

    Book getByName(String name);

}
