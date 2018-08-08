package com.senla.mainmodule.util.fileworker.csvworker.merger;

import com.senla.mainmodule.entities.Book;
import com.senla.mainmodule.services.IService;

import java.util.List;

public class MergerBook extends Merger{

    private List<Book> repoList;

    public MergerBook(IService<Book> service) {
        super(service);
        repoList = service.getRepo();
    }

    void splitImportList() {
        for (Book importList : (List<Book>)getImportList()) {
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
        for (Book book : (List<Book>) getExistList()) {
            book.setId(++newId);
            getFinalList().add(book);
        }
    }

    private Long findMaxId() {
        Long maxId = 0L;
        for (Book book : (List<Book>) getFinalList()) {
            if (book.getId() > maxId) {
                maxId = book.getId();
            }
        }
        return maxId;
    }
}
