package com.senla.fileworker.imports;

import entities.Book;

import java.util.List;

public interface IImportBookFromCsv {

    List<Book> importListFromFile(String path);
}
