package com.cafe.services;

import com.cafe.api.dao.IGenericDao;
import com.cafe.api.dao.IStorageDao;
import com.cafe.api.services.IStorageService;
import com.cafe.model.Goods;
import com.cafe.model.Order;
import com.cafe.model.Storage;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional
public class StorageService implements IStorageService {

    @Autowired
    private IStorageDao storageDao;

    @Override
    public void add(Storage storage) {
        storageDao.add(storage);
    }

    @Override
    public void update(Storage storage) {
        storageDao.update(storage);
    }

    @Override
    public void delete(Long id) {
        storageDao.delete(id);
    }

    @Override
    public Storage getById(Long id) {
        return storageDao.getById(id);
    }

    @Override
    public List<Storage> getAll() {
        return storageDao.getAll();
    }

    @Override
    public Storage getByName(String name) {
        return storageDao.getByName(name);
    }

    @Override
    public Storage getQuantityByName(String name) {
        return null;
    }

}
