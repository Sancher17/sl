package com.cafe.dao;

import com.cafe.api.dao.IStorageDao;
import com.cafe.model.Storage;
import org.springframework.stereotype.Repository;

@Repository
public class StorageDao extends AbstractDao<Storage> implements IStorageDao {

    public StorageDao() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }
}
