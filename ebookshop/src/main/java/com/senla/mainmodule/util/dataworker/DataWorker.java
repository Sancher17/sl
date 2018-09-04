package com.senla.mainmodule.util.dataworker;

import com.senla.mainmodule.services.IService;
import entities.Book;
import entities.Order;
import entities.Request;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.senla.mainmodule.constants.Constants.*;

public class DataWorker implements IDataWorker {

    private static final Logger log = Logger.getLogger(DataWorker.class);

    public void writeDataToFile(IService service, List list) {
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

    public List readDataFromFile(String path) {
        List temp = new ArrayList();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            temp = (List) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("Ошибка чтения файла " + e);
        }
        return temp;
    }
}
