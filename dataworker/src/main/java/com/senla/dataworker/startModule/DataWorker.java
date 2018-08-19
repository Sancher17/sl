package com.senla.dataworker.startModule;

import com.senla.dataworker.properties.PropertyHolder;
import com.senla.dataworker.readfile.IReadFromCsv;
import com.senla.dataworker.readfile.ReadFromCsv;
import com.senla.dataworker.writefile.*;

import java.util.ArrayList;
import java.util.List;

public class DataWorker implements IDataWorker {

    private IWriteToCsv writeToCsv;
    private IReadFromCsv readFromCsv;

    public DataWorker() {
        this.writeToCsv = new WriteToCsv();
        this.readFromCsv = new ReadFromCsv();
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
    public List readFromScv(String path) {
        return  new ArrayList<Object>(readFromCsv.runImport());
    }
}
