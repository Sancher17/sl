package com.senla.mainmodule.util.fileworker;

import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.util.fileworker.csvworker.exports.ExportBookToCsv;
import com.senla.mainmodule.util.fileworker.csvworker.exports.ExportOrderToCsv;
import com.senla.mainmodule.util.fileworker.csvworker.exports.ExportRequestToCsv;
import com.senla.mainmodule.util.fileworker.csvworker.exports.IExportCsv;
import com.senla.mainmodule.util.fileworker.csvworker.imports.ImportBookFromCsv;
import com.senla.mainmodule.util.fileworker.csvworker.imports.ImportCsv;
import com.senla.mainmodule.util.fileworker.csvworker.imports.ImportOrderFromCsv;
import com.senla.mainmodule.util.fileworker.csvworker.imports.ImportRequestFromCsv;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class FileWorker {

    private IExportCsv exportBookToCsv = new ExportBookToCsv();
    private IExportCsv exportOrderToCsv = new ExportOrderToCsv();
    private IExportCsv exportRequestToCsv = new ExportRequestToCsv();

    private ImportCsv importBookFromCsv = new ImportBookFromCsv();
    private ImportCsv importOrderFromCsv = new ImportOrderFromCsv();
    private ImportCsv importRequestFromCsv = new ImportRequestFromCsv();


    private static final Logger log = Logger.getLogger(FileWorker.class);


    /** File */
    public void writeToFile(IService service, String path) {
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
