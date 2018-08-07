package com.senla.mainmodule.util;

import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.impl.ServiceBook;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

public class FileWorker {

    private static final Logger log = Logger.getLogger(FileWorker.class);

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
}
