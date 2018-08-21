package com.senla.dataworker.startModule;

import com.senla.dataworker.di.DependencyBuilder;
import com.senla.dataworker.properties.PropertyHolder;
import com.senla.dataworker.readfile.ReadFromCsv;
import com.senla.dataworker.readfile.ReadFromCsvImpl;
import com.senla.dataworker.writefile.*;

import java.util.ArrayList;
import java.util.List;

public class DataWorkerImpl implements DataWorker {

    private WriteToCsv writeToCsv;
    private ReadFromCsv readFromCsv;

    public DataWorkerImpl() {
        this.writeToCsv = DependencyBuilder.getBean(WriteToCsv.class);
        this.readFromCsv = DependencyBuilder.getBean(ReadFromCsv.class);
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
