package com.senla.api.fileworker;

import com.senla.api.dao.IGenericDao;
import org.hibernate.Session;

import java.util.List;

public interface IFileWorker<T> {

    void exportToCsv(List<T> list);

    List importListFromFile(String path, Session session, Class<T> clazz, IGenericDao dao);

}
