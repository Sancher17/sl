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

    private IGenericDao<Storage> getDao() {
        return storageDao;
    }

    @Override
    public void add(Storage storage) {
        getDao().add(storage);
    }

    @Override
    public void update(Storage storage) {
        getDao().update(storage);
    }

    @Override
    public void delete(Long id) {
        getDao().delete(id);
    }

    @Override
    public Storage getById(Long id) {
        return getDao().getById(id);
    }

    @Override
    public List<Storage> getAll() {
        return getDao().getAll();
    }

    @Override
    public Storage getByName(String name) {
        return getDao().getByName(name);
    }

    @Override
    public Storage getQuantityByName(String name) {
        return null;
    }

}
