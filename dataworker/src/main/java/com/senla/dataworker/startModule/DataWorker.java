package com.senla.dataworker.startModule;

import com.senla.dataworker.properties.PropertyHolder;
import com.senla.dataworker.readfile.IReadFromCsv;
import com.senla.dataworker.readfile.ReadFromCsv;
import com.senla.dataworker.writefile.*;

import java.util.List;
import java.util.Map;

import static com.senla.dataworker.constants.ConstantsDataWorker.*;

public class DataWorker implements IDataWorker {


    private IWriteToCsv writeToCsv;
//    private IReadFromCsv readFromCsv = new ReadFromCsv();

    public DataWorker() {
        this.writeToCsv = new WriteToCsv();
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
//        readFromCsv.read();
    }
}
