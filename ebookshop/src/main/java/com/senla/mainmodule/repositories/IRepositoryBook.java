package com.senla.mainmodule.repositories;

import entities.Book;


public interface IRepositoryBook  extends IRepository {

    Book getByName(String name);
}
