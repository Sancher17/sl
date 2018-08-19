package com.senla.mainmodule.util.fileworker.csvworker.exports;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;

public abstract class Export implements IExportCsv {

    private static final Logger log = Logger.getLogger(Export.class);

    abstract public String prepareData();

    public void exportToFile(String path) {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(path), Charset.forName("Windows-1251")))) {
            pw.write(prepareData());
        } catch (FileNotFoundException e) {
            log.error("Не найден файл для сохранения " + e);
        }
    }
}
