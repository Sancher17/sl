package com.senla.mainmodule.services;

import java.util.List;

public interface IService<T> {

    List<T> getRepo();

    void setRepo(List<T> list);

    void setLastId();
}
