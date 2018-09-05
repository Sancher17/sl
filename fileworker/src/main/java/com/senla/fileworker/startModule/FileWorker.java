package com.senla.fileworker.startModule;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.exports.IExportToCsv;
import com.senla.propertiesmodule.IPropertyHolder;

import java.util.List;

public class FileWorker implements IFileWorker {

    private IExportToCsv IExportToCsv;
    private IPropertyHolder properties;

    public FileWorker() {
        this.IExportToCsv = DependencyInjection.getBean(IExportToCsv.class);
        this.properties = DependencyInjection.getBean(IPropertyHolder.class);
        properties.pathsForCsvFiles();
    }

    @Override
    public void exportToCsv(List list) {
        IExportToCsv.write(list);
    }
}
