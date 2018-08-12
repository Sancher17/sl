package com.senla.dataworker.properties;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.senla.dataworker.constants.ConstantsDataWorker.*;

public class PropertyHolder {

    private static final Logger log = Logger.getLogger(PropertyHolder.class);
    private Properties property = new Properties();

    public void pathsForFiles(){
        loadProperties();
        PATH_BOOK_CSV = property.getProperty("PATH_BOOK_FILE");
        PATH_ORDER_CSV = property.getProperty("PATH_ORDER_FILE");
        PATH_REQUEST_CSV = property.getProperty("PATH_REQUEST_FILE");
    }

    private void loadProperties() {
        try (FileInputStream fis = new FileInputStream(PATH_FILE_PROPERTIES)){
            property.load(fis);
        } catch (FileNotFoundException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            log.error("Файл свойств отсуствует!" + e);
        } catch (IOException e) {
            log.error("Ошибка FileInputStream" + e);
        }
    }
}
