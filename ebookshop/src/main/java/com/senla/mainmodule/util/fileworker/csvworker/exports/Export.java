package com.senla.mainmodule.util.fileworker.csvworker.exports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class Export implements IExportCsv {

    abstract public String prepareData();

    public void exportToFile(String path) {
        try (PrintWriter pw = new PrintWriter(new File(path))) {
            pw.write(prepareData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
