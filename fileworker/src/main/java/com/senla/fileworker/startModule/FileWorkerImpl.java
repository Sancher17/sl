package com.senla.fileworker.startModule;

import com.senla.fileworker.di.DependencyBuilder;
import com.senla.fileworker.imports.ImportFromCsv;
import com.senla.fileworker.exports.*;
import com.senla.propertiesmodule.PropertyHolder;

import java.util.ArrayList;
import java.util.List;

public class FileWorkerImpl implements FileWorker {

    private ExportToCsv exportToCsv;
    private ImportFromCsv importFromCsv;
    private PropertyHolder properties = new PropertyHolder();

    public FileWorkerImpl() {
        this.exportToCsv = DependencyBuilder.getBean(ExportToCsv.class);
        this.importFromCsv = DependencyBuilder.getBean(ImportFromCsv.class);
        properties.pathsForCsvFiles();
    }

    @Override
    public void exportToCsv(List list) {
        exportToCsv.write(list);
    }

    @Override
    public List importFromScv(String path) {
        return new ArrayList<Object>(importFromCsv.runImport());
    }
}
