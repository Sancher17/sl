package com.senla.fileworker.exports;

import java.util.List;

public interface IExportToCsv<T> {

    void write(List<T> list);
}
