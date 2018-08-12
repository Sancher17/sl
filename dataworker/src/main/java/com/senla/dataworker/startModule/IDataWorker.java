package com.senla.dataworker.startModule;

import java.util.List;

public interface IDataWorker <T> {

    void writeToCsv(List<T> list);

    void readFromCsc();

    void writeBookToCsv(List<T> list);
}
