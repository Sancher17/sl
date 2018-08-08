package com.senla.mainmodule.util.fileworker.csvworker.merger;

import com.senla.mainmodule.entities.Order;
import com.senla.mainmodule.entities.Request;
import com.senla.mainmodule.services.IService;
import com.senla.mainmodule.services.IServiceOrder;
import com.senla.mainmodule.services.IServiceRequest;
import com.senla.mainmodule.services.impl.ServiceOrder;
import com.senla.mainmodule.services.impl.ServiceRequest;

import java.util.List;

public class MergerRequest extends Merger {

    private List<Request> repoList;

    public MergerRequest(IService<Request> service) {
        super(service);
        repoList = service.getRepo();
    }

    void splitImportList() {
        for (Request importList : (List<Request>) getImportList()) {
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
        for (Request request : (List<Request>) getExistList()) {
            request.setId(++newId);
            getFinalList().add(request);
        }
    }

    private Long findMaxId() {
        Long maxId = 0L;
        for (Request request : (List<Request>) getFinalList()) {
            if (request.getId() > maxId) {
                maxId = request.getId();
            }
        }
        return maxId;
    }
}
