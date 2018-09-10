package com.senla.services.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.repositories.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.senla.mainmodule.constants.Constants.*;

public abstract class Service<T> extends Observable {

    private List<Observer> subscribers = new ArrayList<>();
    private IFileWorker IFileWorker = DependencyInjection.getBean(IFileWorker.class);

    @Override
    public void addObserver(Observer o) {
        subscribers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        subscribers.remove(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        for (Observer observer : subscribers) {
            observer.update(this, arg);
        }
    }

    void writeToCsv(List list){
        IFileWorker.exportToCsv(list);
        notifyObservers("Файл сохранен в папку: " + PATH_FOR_CSV + FILE_NAME);
    }

    void merge(List<T> importlist, IRepository<T> repository) {
        List<T> listExistingEntry = new ArrayList<>();
        List<T> listNotExistingEntry = new ArrayList<>();
        boolean exist;
        for (T element : importlist) {
            exist = false;
            for (T bookExist : repository.getAll()) {
                if (element.equals(bookExist)) {
                    listExistingEntry.add(element);
                    exist = true;
                }
            }
            if (!exist){
                listNotExistingEntry.add(element);
            }
        }
        for (T element : listExistingEntry) {
            repository.update(element);
        }
        repository.addAll(listNotExistingEntry);
    }
}
