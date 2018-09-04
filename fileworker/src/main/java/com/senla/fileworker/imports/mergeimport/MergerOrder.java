package com.senla.fileworker.imports.mergeimport;

import entities.Order;

import java.util.List;

public class MergerOrder extends Merger<Order> {

    private List<Order> repoList;

    public MergerOrder(List<Order> repoList) {
        this.repoList = repoList;
    }


    void splitImportList() {
        for (Order importList :  getImportList()) {
            Long id = importList.getId(); //проверяем ИД с импорта
            for (Order repo : repoList) {
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
        for (Order order :  getExistList()) {
            order.setId(++newId);
            getFinalList().add(order);
        }
    }

    private Long findMaxId() {
        Long maxId = 0L;
        for (Order order :  getFinalList()) {
            if (order.getId() > maxId) {
                maxId = order.getId();
            }
        }
        return maxId;
    }
}
