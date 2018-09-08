package com.senla.propertiesmodule;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.senla.mainmodule.constants.Constants.*;
import static com.senla.propertiesmodule.constants.ConstantsProperties.PATH_FILE_PROPERTIES;

public class PropertyHolder implements IPropertyHolder {

    private static final Logger log = Logger.getLogger(PropertyHolder.class);
    private static Properties property = new Properties();

    static {
        loadProperties();
    }

    public static void loadProperties() {
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
    public String loadBean(Class<?> clazz) {
        return property.getProperty(String.valueOf(clazz.getSimpleName()));
    }

    @Override
    public void bookIsOld() {
        BOOK_IS_OLD = Integer.valueOf(property.getProperty("BOOK_IS_OLD"));
    }

    @Override
    public void allowMArkRequest() {
        ALLOW_MARK_REQUESTS = Boolean.valueOf(property.getProperty("ALLOW_MARK_REQUESTS"));
    }

    @Override
    public void pathsForDataFiles() {
        PATH_BOOK_DATA = property.getProperty("PATH_BOOK_DATA_FILE");
        PATH_ORDER_DATA = property.getProperty("PATH_ORDER_DATA_FILE");
        PATH_REQUEST_DATA = property.getProperty("PATH_REQUEST_DATA_FILE");
    }

    @Override
    public void pathsForCsvFiles() {
        PATH_FOR_CSV = property.getProperty("PATH_EXPORT_TO_CSV");
    }
}
