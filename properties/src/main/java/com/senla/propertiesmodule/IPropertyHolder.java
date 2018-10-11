package com.senla.propertiesmodule;

public interface IPropertyHolder {

    String loadBean(Class<?> clazz);

    void bookIsOld();

    void allowMArkRequest();

    void pathsForCsvFiles();

    void dbConnection();

}
