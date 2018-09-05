package com.senla.util.dataworker;


import com.senla.services.IService;

import java.util.List;

public interface IDataWorker<T> {

    void writeDataToFile(IService service, List list);

    List<T> readDataFromFile(String path);
}
