package com.senla.fileworker.startModule;

import java.util.List;

public interface IFileWorker<T> {

    void exportToCsv(List<T> list);

    List importListFromFile(String path, Class<T> clazz);
}
