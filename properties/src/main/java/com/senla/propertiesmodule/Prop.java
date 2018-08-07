package com.senla.propertiesmodule;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.senla.mainmodule.constants.Constants.*;
import static com.senla.propertiesmodule.constants.ConstantsProperties.PATH_FILE_PROPERTIES;

public class Prop {

    private static final Logger log = Logger.getLogger(Prop.class);

    private FileInputStream fis;
    private Properties property = new Properties();

    private void loadProperties() {
        try {
            fis = new FileInputStream(PATH_FILE_PROPERTIES);
            property.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            log.error("Файл свойств отсуствует!" + e);
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
}
