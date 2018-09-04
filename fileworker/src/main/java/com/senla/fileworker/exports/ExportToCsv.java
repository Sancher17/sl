package com.senla.fileworker.exports;

import java.util.List;

public interface ExportToCsv<T> {

    void write(List<T> list);

}
