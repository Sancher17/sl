package com.senla.fileworker.imports;

import java.util.List;

public interface IImportFromCsv<T> {

    List importListFromFile(String path, Class<T> clazz);
}