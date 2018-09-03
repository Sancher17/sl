package com.senla.mainmodule.util.fileworker;

import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.IServiceBook;
import com.senla.mainmodule.util.fileworker.csvworker.exports.Export;
import com.senla.mainmodule.util.fileworker.csvworker.exports.ExportBookToCsv;
import com.senla.mainmodule.util.fileworker.csvworker.exports.ExportOrderToCsv;
import com.senla.mainmodule.util.fileworker.csvworker.exports.ExportRequestToCsv;
import com.senla.mainmodule.util.fileworker.csvworker.imports.IImportCsv;
import com.senla.mainmodule.util.fileworker.csvworker.imports.ImportBookFromCsv;
import com.senla.mainmodule.util.fileworker.csvworker.imports.ImportOrderFromCsv;
import com.senla.mainmodule.util.fileworker.csvworker.imports.ImportRequestFromCsv;
import entities.Book;
import entities.Order;
import entities.Request;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class FileWorker implements IFileWorker {

    private static final Logger log = Logger.getLogger(FileWorker.class);

    private Export exportList;
    private IImportCsv importList;

    //File
    public void writeToFile(IService service, List list) {
        String path = "";
        Object obj = list.get(0);
        Class<?> clazz = obj.getClass();
        String nameClass = clazz.getSimpleName();
        if (nameClass.equals(Book.class.getSimpleName())) {
            path = PATH_BOOK_DATA_TEST;
        } else if (nameClass.equals(Order.class.getSimpleName())) {
            path = PATH_ORDER_DATA_TEST;
        } else if (nameClass.equals(Request.class.getSimpleName())) {
            path = PATH_REQUEST_DATA_TEST;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(list);
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

    //CSV
    public void exportToCsv(IService service, List list) {
        Object obj = list.get(0);
        Class<?> clazz = obj.getClass();
        String nameClass = clazz.getSimpleName();
        String path = setInstances(service, nameClass);
        exportList.exportToFile(path);
    }

    public void importFromCsv(IService service, List list) {
        Object obj = list.get(0);
        Class<?> clazz = obj.getClass();
        String nameClass = clazz.getSimpleName();
        String path = setInstances(service, nameClass);
        importList.importFromFile(path);
    }

    public void importFromCsv(IService service, IServiceBook serviceBook, List list) {
        Object obj = list.get(0);
        Class<?> clazz = obj.getClass();
        String nameClass = clazz.getSimpleName();
        String path = setInstances(service, nameClass);
        importList = new ImportOrderFromCsv(service, serviceBook);
        importList.importFromFile(path);
    }

    private String setInstances(IService service, String nameClass) {
        if (nameClass.equals(Book.class.getSimpleName())) {
            importList = new ImportBookFromCsv(service);
            exportList = new ExportBookToCsv(service);
            return PATH_BOOK_CSV;
        } else if (nameClass.equals(Order.class.getSimpleName())) {
            exportList = new ExportOrderToCsv(service);
            return PATH_ORDER_CSV;
        } else if (nameClass.equals(Request.class.getSimpleName())) {
            importList = new ImportRequestFromCsv(service);
            exportList = new ExportRequestToCsv(service);
            return PATH_REQUEST_CSV;
        }
        return "";
    }
}
