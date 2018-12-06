package com.cafe.dao;

import com.cafe.api.dao.IStorageDao;
import com.cafe.model.GenericEntity;
import com.cafe.model.Storage;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class StorageDao extends AbstractDao<Storage> implements IStorageDao {

    @Override
    public Class<Storage> getChildClass() {
        return Storage.class;
    }

}
