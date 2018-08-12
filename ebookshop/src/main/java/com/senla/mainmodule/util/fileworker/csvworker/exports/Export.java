package com.senla.mainmodule.util.fileworker.csvworker.exports;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class Export implements IExportCsv {

    private static final Logger log = Logger.getLogger(Export.class);

    abstract public String prepareData();

    public void exportToFile(String path) {
        try (PrintWriter pw = new PrintWriter(new File(path))) {
            pw.write(prepareData());
        } catch (FileNotFoundException e) {
            log.error("Не найден файл для сохранения " + e);
        }
    }
}
