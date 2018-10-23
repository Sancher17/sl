package com.senla.fileworker.startModule;

import com.senla.api.dao.IGenericDao;
import com.senla.api.fileworker.IFileWorker;
import com.senla.api.fileworker.exports.IExportToCsv;
import com.senla.api.fileworker.imports.IImportFromCsv;
import com.senla.propertiesmodule.IPropertyHolder;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileWorker implements IFileWorker {

    private IExportToCsv exportToCsv;
    private IImportFromCsv importFromCsv;

    public FileWorker(IExportToCsv exportToCsv, IImportFromCsv importFromCsv, IPropertyHolder properties) {
        this.exportToCsv = exportToCsv;
        this.importFromCsv = importFromCsv;
        properties.pathsForCsvFiles();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void exportToCsv(List list) {
        exportToCsv.write(list);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List importListFromFile(String path, Session session, Class clazz, IGenericDao dao) {
        return importFromCsv.importListFromFile(path, session, clazz, dao);
    }
}
