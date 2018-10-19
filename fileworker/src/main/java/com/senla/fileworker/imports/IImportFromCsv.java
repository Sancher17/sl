package com.senla.fileworker.imports;

import org.hibernate.Session;

import java.util.List;

public interface IImportFromCsv<T> {

    List importListFromFile(String path, Session session, Class<T> clazz);
}