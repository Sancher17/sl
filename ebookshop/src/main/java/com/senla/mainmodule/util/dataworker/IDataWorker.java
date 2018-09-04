package com.senla.mainmodule.util.dataworker;

import com.senla.mainmodule.services.IService;

import java.util.List;

public interface IDataWorker<T> {

    void writeDataToFile(IService service, List list);

    List<T> readDataFromFile(String path);
}
