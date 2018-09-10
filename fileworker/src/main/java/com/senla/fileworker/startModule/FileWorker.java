package com.senla.fileworker.startModule;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.exports.IExportToCsv;
import com.senla.fileworker.imports.IImportFromCsv;
import com.senla.propertiesmodule.IPropertyHolder;

import java.util.List;
import java.util.Objects;

public class FileWorker implements IFileWorker {

    private IExportToCsv exportToCsv;
    private IImportFromCsv importFromCsv;
    private IPropertyHolder properties;

    public FileWorker() {
        this.exportToCsv = DependencyInjection.getBean(IExportToCsv.class);
        this.importFromCsv = DependencyInjection.getBean(IImportFromCsv.class);
        this.properties = DependencyInjection.getBean(IPropertyHolder.class);
        Objects.requireNonNull(properties, "IPropertyHolder = null").pathsForCsvFiles();
    }

    @Override
    public void exportToCsv(List list) {
        exportToCsv.write(list);
    }

    @Override
    public List importListFromFile(String path, Class clazz) {
        return importFromCsv.importListFromFile(path, clazz);
    }
}
