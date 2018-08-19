package com.senla.mainmodule.util.fileworker;

import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.IServiceBook;

import java.util.List;

public interface IFileWorker {

    void writeToFile(IService service, List list);

    void readFromFile(IService service, String path);

    void exportToCsv(IService service, List list);

    void importFromCsv(IService service, List list);

    void importFromCsv(IService service, IServiceBook serviceBook, List list);
}
