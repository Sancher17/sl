package com.senla.mainmodule.util.fileworker.csvworker.imports;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public abstract class ImportCsv implements IImportCsv {

    private List<String> tempDataString = new ArrayList<>();

    public abstract void importFromFile(String path);

    abstract void createObjectList();

    void read(String path) {
        String st;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(path), Charset.forName("Windows-1251")))) {
            while ((st = br.readLine()) != null) {
                tempDataString.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTempDataString() {
        return tempDataString;
    }
}
