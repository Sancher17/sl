package com.senla.mainmodule.repositories;

import com.senla.mainmodule.entities.Book;


public interface IRepositoryBook  extends IRepository {

    Book getByName(String name);
}
