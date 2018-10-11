package com.senla.fileworker.imports.impl;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

abstract class ImportCsv {

    private static final Logger log = Logger.getLogger(ImportCsv.class);

    private static final String READ_FILE_ERROR = "Ошибка при чтении файла ";
    private static final String WINDOWS_1251 = "Windows-1251";

    List<String> read(String path) {
        List<String> tempDataString = new ArrayList<>();
        String st;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(path), Charset.forName(WINDOWS_1251)))) {
            while ((st = br.readLine()) != null) {
                tempDataString.add(st);
            }
        } catch (IOException e) {
            log.error(READ_FILE_ERROR + e);
        }
        return tempDataString;
    }
}
