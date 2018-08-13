package com.senla.dataworker.startModule;

import com.senla.dataworker.properties.PropertyHolder;
import com.senla.dataworker.writefile.*;

import java.util.List;

import static com.senla.dataworker.constants.ConstantsDataWorker.*;

public class DataWorker implements IDataWorker {

    private IWriteToCsv writeToCsv = new WriteToCsv();

    public DataWorker() {
        initModule();
    }

    private void initModule() {
        PropertyHolder properties = new PropertyHolder();
        properties.pathsForFiles();
    }

    @Override
    public void writeToCsv(List list) {
        writeToCsv.write(list);
    }

    @Override
    public void readFromScv(String path) {
        // TODO: 13.08.2018
    }
}
