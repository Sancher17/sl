package com.senla.fileworker.imports;

import entities.Order;

import java.util.List;

public interface IImportOrderFromCsv {

    List<Order> importListFromFile(String path);
}
