package com.senla.fileworker.imports.impl;

import com.senla.fileworker.imports.IImportRequestFromCsv;
import entities.Request;

import java.util.ArrayList;
import java.util.List;


public class ImportRequestFromCsv extends ImportCsv implements IImportRequestFromCsv {

    @Override
    public List<Request> importListFromFile(String path) {
        List<Request> tempList = new ArrayList<>();
        List<String> list = read(path);
        list.remove(0);
        for (String str : list) {
            String[] temp = str.split(";");
            String id = temp[0];
            String requireNameBook = temp[1];
            String requireIsCompleted = temp[2];
            String requireQuantity = temp[3];

            Request request = new Request();
            request.setId(Long.valueOf(id));
            request.setRequireNameBook(requireNameBook);
            request.setRequireIsCompleted(Boolean.valueOf(requireIsCompleted));
            request.setRequireQuantity(Integer.valueOf(requireQuantity));

            tempList.add(request);
        }
        return tempList;
    }
}
