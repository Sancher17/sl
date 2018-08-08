package com.senla.mainmodule.util.fileworker.csvworker.merger;

import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.services.IService;

import java.util.List;

public class MergerOrder extends Merger {

    private List<Order> repoList;

    public MergerOrder(IService<Order> service) {
        super(service);
        repoList = service.getRepo();
    }


    void splitImportList() {
        for (Order importList : (List<Order>) getImportList()) {
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
        for (Order order : (List<Order>) getExistList()) {
            order.setId(++newId);
            getFinalList().add(order);
        }
    }

    private Long findMaxId() {
        Long maxId = 0L;
        for (Order order : (List<Order>) getFinalList()) {
            if (order.getId() > maxId) {
                maxId = order.getId();
            }
        }
        return maxId;
    }
}
