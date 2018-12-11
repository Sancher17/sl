package com.cafe.dao;

import com.cafe.api.dao.IGoodsDao;
import com.cafe.dao.util.HibernateUtil;
import com.cafe.model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public class GoodsDao extends AbstractDao<Goods> implements IGoodsDao {

    protected GoodsDao() {
        super(Goods.class);
    }

    @Override
    public List<Goods> getListByName(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery(Goods.class);
        Root<Goods> root = criteria.from(Goods.class);
        root.fetch(Goods_.NAME_GOODS);
        root.fetch(Goods_.CATEGORY);
        criteria.where(builder.equal(root.get(Goods_.nameGoods).get(NameGoods_.name), name));
        Query<Goods> query = getSession().createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Goods> getAll() {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery(Goods.class);
        Root<Goods> root = getGoodsRoot(criteria);
        criteria.select(root);
        Query<Goods> query = getSession().createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Goods getById(Long id) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery(Goods.class);
        Root<Goods> root = getGoodsRoot(criteria);
        criteria.where(builder.equal(root.get(Goods_.ID), id));
        Query<Goods> query = getSession().createQuery(criteria);
        return query.getSingleResult();
    }

    private Root<Goods> getGoodsRoot(CriteriaQuery<Goods> criteria) {
        Root<Goods> root = criteria.from(Goods.class);
        root.fetch(Goods_.NAME_GOODS);
        root.fetch(Goods_.CATEGORY);
        criteria.select(root);
        return root;
    }
}
