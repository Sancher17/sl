package com.senla.propertiesmodule;

import com.senla.constants.Constants;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.senla.propertiesmodule.constants.ConstantsProperties.*;

public class PropertyHolder implements IPropertyHolder {

    private static final Logger log = Logger.getLogger(PropertyHolder.class);

    private static final String NO_PROPERTY_FILE = "Файл свойств отсуствует!";
    private static final String ERROR_FILE_INPUT_STREAM = "Ошибка FileInputStream";
    private static final String BOOK_IS_OLD = "BOOK_IS_OLD";
    private static final String ALLOW_MARK_REQUESTS = "ALLOW_MARK_REQUESTS";
    private static final String PATH_EXPORT_TO_CSV = "PATH_EXPORT_TO_CSV";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";
    private static final String URL = "URL";

    private  Properties property = new Properties();

    public PropertyHolder() {
        loadProperties();
    }

    private void loadProperties() {
        try (FileInputStream fis = new FileInputStream(PATH_FILE_PROPERTIES)) {
            property.load(fis);
        } catch (FileNotFoundException e) {
            log.error(NO_PROPERTY_FILE + e);
        } catch (IOException e) {
            log.error(ERROR_FILE_INPUT_STREAM + e);
        }
    }

    @Override
    public String loadBean(Class<?> clazz) {
        return property.getProperty(String.valueOf(clazz.getSimpleName()));
    }

    @Override
    public void bookIsOld() {
        Constants.BOOK_IS_OLD = Integer.valueOf(property.getProperty(BOOK_IS_OLD));
    }

    @Override
    public void allowMArkRequest() {
        Constants.ALLOW_MARK_REQUESTS = Boolean.valueOf(property.getProperty(ALLOW_MARK_REQUESTS));
    }

    @Override
    public void pathsForCsvFiles() {
        Constants.PATH_FOR_CSV = property.getProperty(PATH_EXPORT_TO_CSV);
    }

    @Override
    public void dbConnection() {
        Constants.USER = property.getProperty(USER);
        Constants.PASSWORD = property.getProperty(PASSWORD);
        Constants.URL = property.getProperty(URL);
    }
}
