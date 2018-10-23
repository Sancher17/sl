package com.senla.api.fileworker.exports;

import java.util.List;

public interface IExportToCsv<T> {

    void write(List<T> list);
}
