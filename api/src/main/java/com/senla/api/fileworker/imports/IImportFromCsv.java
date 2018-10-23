package com.senla.api.fileworker.imports;

import com.senla.api.dao.IGenericDao;
import org.hibernate.Session;

import java.util.List;

public interface IImportFromCsv<T> {

    List importListFromFile(String path, Session session, Class<T> clazz, IGenericDao dao);
}