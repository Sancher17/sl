package com.senla.fileworker.imports.mergeimport;

import java.util.ArrayList;
import java.util.List;

public abstract class Merger<T> {

    private List<T> importList = new ArrayList<>();
    private List<T> existList = new ArrayList<>();
    private List<T> notExistList = new ArrayList<>();
    private List<T> finalList = new ArrayList<>();
    private Boolean exist = false;


    public List<T> merge(List list) {
        importList.addAll(list);
        splitImportList();//делим импорт на два list: existList(id - совпало) и notExistList(id - не совпало)
        createFinalList();//создаем новый обобщенный список (repoList + notExistList)
        handleExistList();//обрабатываем список в котором совпали id // дополняем finalList новыми записями с новыми id
        return finalList;
    }

    abstract void splitImportList();

    abstract void createFinalList();

    abstract void handleExistList();


    public List<T> getImportList() {
        return importList;
    }

    public List<T> getExistList() {
        return existList;
    }

    public List<T> getNotExistList() {
        return notExistList;
    }

    public List<T> getFinalList() {
        return finalList;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }
}
