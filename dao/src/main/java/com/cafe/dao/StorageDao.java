package com.cafe.dao;

import com.cafe.api.dao.IStorageDao;
import com.cafe.model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StorageDao extends AbstractDao<Storage> implements IStorageDao {

    public StorageDao() {
        super(Storage.class);
    }

    @Override
    public List<Storage> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Storage> criteria = builder.createQuery(Storage.class);
        Root<Storage> root = criteria.from(Storage.class);
        Fetch<Storage, Goods> goods = root.fetch(Storage_.goods);
        goods.fetch(Goods_.nameGoods);
        goods.fetch(Goods_.category);
        criteria.select(root);
        Query<Storage> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
