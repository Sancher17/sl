package com.senla.services.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Service extends Observable {

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
}
