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

    protected StorageDao() {
        super(Storage.class);
    }

    @Override
    public List<Storage> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Storage> criteria = builder.createQuery(Storage.class);
        Root<Storage> root = getStorageRoot(criteria);
        criteria.select(root);
        Query<Storage> query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Storage getById(Long id) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<Storage> criteria = builder.createQuery(Storage.class);
        Root<Storage> root = getStorageRoot(criteria);
        criteria.where(builder.equal(root.get(Storage_.ID), id));
        Query<Storage> query = getSession().createQuery(criteria);
        return query.getSingleResult();
    }

    private Root<Storage> getStorageRoot(CriteriaQuery<Storage> criteria) {
        Root<Storage> root = criteria.from(Storage.class);
        Fetch<Storage, Goods> goods = root.fetch(Storage_.goods);
        goods.fetch(Goods_.nameGoods);
        goods.fetch(Goods_.category);
        return root;
    }
}
