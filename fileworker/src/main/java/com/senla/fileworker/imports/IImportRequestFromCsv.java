package com.senla.fileworker.imports;

import entities.Request;

import java.util.List;

public interface IImportRequestFromCsv {

    List<Request> importListFromFile(String path);
}
