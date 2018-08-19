package com.senla.mainmodule.services;

import java.util.List;
import java.util.Observer;

public interface IService<T> {

    List<T> getRepo();

    void setRepo(List<T> list);

    void setLastId();

    void addObserver(Observer o);

    void deleteObserver(Observer o);
}
