package com.senla.dataworker.startModule;

import com.senla.dataworker.properties.PropertyHolder;
import com.senla.dataworker.writefile.IWriteToCsv;
import com.senla.dataworker.writefile.WriteBookToCsv;
import com.senla.dataworker.writefile.WriteToCsv;

import java.util.List;

public class DataWorker implements IDataWorker {

    private IWriteToCsv writeToCsv = new WriteBookToCsv();

//    PATH_BOOK_FILE
//    PATH_ORDER_FILE
//    PATH_REQUEST_FILE

    public DataWorker() {
        initModule();
    }

    private void initModule() {
        PropertyHolder properties = new PropertyHolder();
        properties.pathsForFiles();
    }


    @Override
    public void readFromCsc() {

    }

    @Override
    public void writeBookToCsv(List list) {
        writeToCsv.writeToCsv(list, );
    }
}
