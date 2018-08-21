package com.senla.dataworker.startModule;

import java.util.List;

public interface DataWorker<T> {

    void writeToCsv(List<T> list);

    List readFromScv(String path);
}
