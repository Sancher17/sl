package com.senla.propertiesmodule;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.senla.mainmodule.constants.Constants.*;
import static com.senla.propertiesmodule.constants.ConstantsProperties.PATH_FILE_PROPERTIES;
import static com.senla.propertiesmodule.constants.ConstantsProperties.PATH_FOR_CSV;

public class PropertyHolder implements IPropertyHolder {

    private static final Logger log = Logger.getLogger(PropertyHolder.class);
    private Properties property = new Properties();

    private void loadProperties() {
        try (FileInputStream fis = new FileInputStream(PATH_FILE_PROPERTIES)) {
            property.load(fis);
        } catch (FileNotFoundException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            log.error("Файл свойств отсуствует!" + e);
        } catch (IOException e) {
            log.error("Ошибка FileInputStream" + e);
        }
    }

    @Override
    public void bookIsOld() {
        loadProperties();
        BOOK_IS_OLD = Integer.valueOf(property.getProperty("BOOK_IS_OLD"));
    }

    @Override
    public void allowMArkRequest() {
        loadProperties();
        ALLOW_MARK_REQUESTS = Boolean.valueOf(property.getProperty("ALLOW_MARK_REQUESTS"));
    }

    @Override
    public void pathsForDataFiles() {
        loadProperties();
        PATH_BOOK_DATA = property.getProperty("PATH_BOOK_DATA_FILE");
        PATH_ORDER_DATA = property.getProperty("PATH_ORDER_DATA_FILE");
        PATH_REQUEST_DATA = property.getProperty("PATH_REQUEST_DATA_FILE");
    }

    @Override
    public void pathsForCsvFiles() {
        loadProperties();
        PATH_FOR_CSV = property.getProperty("PATH_FOR_CSV_FILES");
    }
}
