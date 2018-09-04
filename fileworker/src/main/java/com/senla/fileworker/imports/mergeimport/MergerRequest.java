package com.senla.fileworker.imports.mergeimport;

import entities.Request;

import java.util.List;

public class MergerRequest extends Merger<Request> {

    private List<Request> repoList;

    public MergerRequest(List<Request> repoList) {
        this.repoList = repoList;
    }

    void splitImportList() {
        for (Request importList :  getImportList()) {
            Long id = importList.getId(); //проверяем ИД с импорта
            for (Request repo : repoList) {
                if (id.equals(repo.getId())) {
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
        for (Request request : getExistList()) {
            request.setId(++newId);
            getFinalList().add(request);
        }
    }


    private Long findMaxId() {
        Long maxId = 0L;
        for (Request request : getFinalList()) {
            if (request.getId() > maxId) {
                maxId = request.getId();
            }
        }
        return maxId;
    }
}
