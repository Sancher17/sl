package com.senla.propertiesmodule;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.senla.mainmodule.constants.Constants.*;
import static com.senla.propertiesmodule.constants.ConstantsProperties.PATH_FILE_PROPERTIES;

public class PropertyHolder {

    private static final Logger log = Logger.getLogger(PropertyHolder.class);
    private Properties property = new Properties();

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

    public void bookIsOld() {
        loadProperties();
        BOOK_IS_OLD = Integer.valueOf(property.getProperty("BOOK_IS_OLD"));
    }

    public void allowMArkRequest() {
        loadProperties();
        ALLOW_MARK_REQUESTS = Boolean.valueOf(property.getProperty("ALLOW_MARK_REQUESTS"));
    }

    public void pathsForFiles(){
        loadProperties();
        PATH_BOOK_DATA = property.getProperty("PATH_BOOK_FILE");
        PATH_ORDER_DATA = property.getProperty("PATH_ORDER_FILE");
        PATH_REQUEST_DATA = property.getProperty("PATH_REQUEST_FILE");
    }
}
