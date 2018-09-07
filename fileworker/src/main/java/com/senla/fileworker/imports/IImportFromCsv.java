package com.senla.fileworker.imports;

import java.util.List;

public interface IImportFromCsv<T> {

    List<T> importListFromFile(String path, Class<T> clazz);
}