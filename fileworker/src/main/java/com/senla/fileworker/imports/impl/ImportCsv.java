package com.senla.fileworker.imports.impl;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public abstract class ImportCsv {

    private static final Logger log = Logger.getLogger(ImportCsv.class);

    List<String> read(String path) {
        List<String> tempDataString = new ArrayList<>();
        String st;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(path), Charset.forName("Windows-1251")))) {
            while ((st = br.readLine()) != null) {
                tempDataString.add(st);
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении файла " + e);
        }
        return tempDataString;
    }
}
