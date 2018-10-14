package com.senla.services.impl;

import com.senla.db.GenericDAO;
import com.senla.db.connection.ConnectionDB;
import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.senla.constants.Constants.*;


public abstract class Service<T> extends Observable {

    static final String NO_DATA_FROM_BD = "Не удалось получить данные с БД ";
    static final String CAN_NOT_ADD_DATA_TO_BD = "Не удачная попытка добавить данные в БД ";
    static final String CAN_NOT_DO_ROLLBACK = "Не удачная попытка сделать rollback ";
    private static final String FILE_SAVED_IN_FOLDER = "Файл сохранен в папку: ";

    private static final Logger log = Logger.getLogger(Service.class);

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

    void merge(List<T> importlist, GenericDAO<T> dao) {
        Connection connection = ConnectionDB.getConnection();
        List<T> listExistingEntry = new ArrayList<>();
        List<T> listNotExistingEntry = new ArrayList<>();
        boolean exist;
        try {
            for (T element : importlist) {
                exist = false;
                for (T bookExist : dao.getAll(connection)) {
                    if (element.equals(bookExist)) {
                        listExistingEntry.add(element);
                        exist = true;
                    }
                }
                if (!exist) {
                    listNotExistingEntry.add(element);
                }
            }
            for (T element : listExistingEntry) {
                dao.update(connection, element);
            }
            dao.addAll(connection, listNotExistingEntry);
        } catch (SQLException e) {
            log.error(CAN_NOT_ADD_DATA_TO_BD + e);
            notifyObservers(CAN_NOT_ADD_DATA_TO_BD);
        }
    }
}
