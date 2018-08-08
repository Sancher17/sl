package com.senla.mainmodule.util.fileworker.csvworker.imports;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class ImportCsv {

    private List<String> tempDataString = new ArrayList<>();

    public abstract void runImport(String path);

    abstract void createObjectList();

    void read(String path) {
        String st;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
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
