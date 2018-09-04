package com.senla.fileworker.imports.mergeimport;

import entities.Book;

import java.util.List;

public class MergerBook extends Merger<Book>{

    private List<Book> repoList;

    public MergerBook(List<Book> repoList) {
        this.repoList = repoList;
    }

    void splitImportList() {
        for (Book importList : getImportList()) {
            Long id = importList.getId(); //проверяем ИД с импорта
            for (Book bookRepo : repoList) {
                if (id.equals(bookRepo.getId())) {
                    getExistList().add(importList);//если есть то в existList
                    setExist(true);
                }
            }
            if (!getExist()) {
                getNotExistList().add(importList);//если нету то в notExistList
            }
        }
    }

    void createFinalList() {
        getFinalList().addAll(repoList);
        getFinalList().addAll(getNotExistList());
    }

    void handleExistList() {
        Long newId = findMaxId();
        for (Book book :  getExistList()) {
            book.setId(++newId);
            getFinalList().add(book);
        }
    }

    private Long findMaxId() {
        Long maxId = 0L;
        for (Book book :  getFinalList()) {
            if (book.getId() > maxId) {
                maxId = book.getId();
            }
        }
        return maxId;
    }
}
