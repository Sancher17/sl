package com.senla.services.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.hibernate.IGenericDao;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.senla.constants.Constants.FILE_NAME;
import static com.senla.constants.Constants.PATH_FOR_CSV;


public abstract class Service<T> extends Observable {

    static final String NO_DATA_FROM_BD = "Не удалось получить данные с БД ";
    static final String CAN_NOT_ADD_DATA_TO_BD = "Не удачная попытка добавить данные в БД ";
    static final String CAN_NOT_WRITE_DATA_TO_FILE = "Не удачная запись данны в файл";
    static final String CAN_NOT_ADD_DATA_FROM_FILE = "Не удачная попытка добавить данные из файла ";

    private static final String FILE_SAVED_IN_FOLDER = "Файл сохранен в папку: ";
    private List<Observer> subscribers = new ArrayList<>();
    private IFileWorker fileWorker = DependencyInjection.getBean(IFileWorker.class);

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

    @SuppressWarnings("unchecked")
    void writeToCsv(List<T> list) {
        fileWorker.exportToCsv(list);
        notifyObservers(FILE_SAVED_IN_FOLDER + PATH_FOR_CSV + FILE_NAME);
    }

    void merge(Session session, List<T> importlist, IGenericDao<T> dao, Class<T> clazz) {
        List<T> listExistingEntry = new ArrayList<>();
        List<T> listNotExistingEntry = new ArrayList<>();
        boolean exist;
        for (T element : importlist) {
            exist = false;
            for (T tExist : dao.getAll(session, clazz)) {
                if (element.equals(tExist)) {
                    listExistingEntry.add(element);
                    exist = true;
                }
            }
            if (!exist) {
                listNotExistingEntry.add(element);
            }
        }
        session.clear();
        for (T element : listExistingEntry) {
            dao.update(session, element);
        }
        dao.addAll(session, listNotExistingEntry);
    }
}
