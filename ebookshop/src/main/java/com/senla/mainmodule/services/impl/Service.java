package com.senla.mainmodule.services.impl;

import com.senla.fileworker.startModule.FileWorker;
import com.senla.fileworker.startModule.FileWorkerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Service extends Observable {

    private List<Observer> subscribers = new ArrayList<>();
    private FileWorker fileWorker = new FileWorkerImpl();

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
        fileWorker.exportToCsv(list);
    }

    public void importFromCsv(String path){
        fileWorker.importFromScv(path);
    }

}
