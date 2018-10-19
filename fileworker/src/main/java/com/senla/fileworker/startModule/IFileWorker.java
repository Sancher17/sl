package com.senla.fileworker.startModule;

import org.hibernate.Session;

import java.util.List;

public interface IFileWorker<T> {

    void exportToCsv(List<T> list);

    List importListFromFile(String path, Session session, Class<T> clazz);

}
