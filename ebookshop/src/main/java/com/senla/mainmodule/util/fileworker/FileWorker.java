package com.senla.mainmodule.util.fileworker;

import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.util.fileworker.csvworker.exports.*;
import com.senla.mainmodule.util.fileworker.csvworker.imports.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.senla.mainmodule.constants.Constants.*;

public class FileWorker {

    private Export exportBookToCsv = new ExportBookToCsv();
    private IExportCsv exportOrderToCsv = new ExportOrderToCsv();
    private IExportCsv exportRequestToCsv = new ExportRequestToCsv();

    private IImportCsv importBookFromCsv = new ImportBookFromCsv();
    private IImportCsv importOrderFromCsv = new ImportOrderFromCsv();
    private IImportCsv importRequestFromCsv = new ImportRequestFromCsv();

    private static final Logger log = Logger.getLogger(FileWorker.class);


    /** File */
    public void writeToFile(IService service, String path) {
        String pat = path;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(service.getRepo());
        } catch (IOException e) {
           log.error("Ошибка записи файла " + e);
        }
    }

    public void readFromFile(IService service, String path) {
        service.getRepo().clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            service.setRepo((List) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            log.error("Ошибка чтения файла " + e);
        }
    }


    /** CSV */
    public void exportBookCsv(){
        exportBookToCsv.exportToFile(PATH_BOOK_CSV);
    }

    public void importBookCsv(){
        importBookFromCsv.runImport(PATH_BOOK_CSV);
    }

    public void exportOrderCsv(){
        exportOrderToCsv.exportToFile(PATH_ORDER_CSV);
    }

    public void importOrderCsv(){
        importOrderFromCsv.runImport(PATH_ORDER_CSV);
    }

    public void exportRequestCsv(){
        exportRequestToCsv.exportToFile(PATH_REQUEST_CSV);
    }

    public void importRequestCsv(){
        importRequestFromCsv.runImport(PATH_REQUEST_CSV);
    }
}
