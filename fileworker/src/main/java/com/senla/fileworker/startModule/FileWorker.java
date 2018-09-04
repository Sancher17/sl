package com.senla.fileworker.startModule;

import java.util.List;

public interface FileWorker<T> {

    void exportToCsv(List<T> list);

    List importFromScv(String path);
}
