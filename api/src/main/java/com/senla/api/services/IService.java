package com.senla.api.services;

import java.util.Observer;

public interface IService<T> {

    void addObserver(Observer o);

    void exportToCsv();

    void importFromCsv();
}
