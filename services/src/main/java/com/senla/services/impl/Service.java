package com.senla.services.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.repositories.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
    }

    public void merge(List<T> importlist, IRepository<T> repository) {
        List<T> existList = new ArrayList<>();
        List<T> notExistList = new ArrayList<>();
        boolean exist;
        for (T entityImpotr : importlist) {
            exist = false;
            for (T bookExist : repository.getAll()) {
                if (entityImpotr.equals(bookExist)) {
                    existList.add(entityImpotr);
                    exist = true;
                }
            }
            if (!exist){
                notExistList.add(entityImpotr);
            }
        }
        for (T book : existList) {
            repository.update(book);
        }
        repository.addAll(notExistList);
    }
}
