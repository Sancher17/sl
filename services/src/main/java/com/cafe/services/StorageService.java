package com.cafe.services;

import com.cafe.api.dao.IStorageDao;
import com.cafe.api.services.IStorageService;
import com.cafe.model.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService implements IStorageService {

    @Autowired
    private IStorageDao storageDao;

    public StorageService() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }

    @Override
    public void add(Storage storage) {

    }

    @Override
    public void update(Storage storage) {

    }

    @Override
    public void delete(Storage storage) {

    }

    @Override
    public Storage getById(Long id) {
        return null;
    }

    @Override
    public List<Storage> getAll() {
        return storageDao.getAll(Storage.class);
    }
}
