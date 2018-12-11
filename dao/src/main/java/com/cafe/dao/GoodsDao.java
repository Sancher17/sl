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

    public GoodsDao() {
        super(Goods.class);
    }

    @Override
    public List<Goods> getListByName(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery(Goods.class);
        Root<Goods> root = criteria.from(Goods.class);
        // TODO: 11.12.2018 проверить запрос - старая версия вроде не работала
//        criteria.where(builder.equal(root.get("name"), name));
        criteria.where(builder.equal(root.get(Goods_.nameGoods).get(NameGoods_.name), name));
        Query<Goods> query = getSession().createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Goods> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery(Goods.class);
        Root<Goods> root = criteria.from(Goods.class);
        root.fetch(Goods_.nameGoods);//case1
        root.fetch(Goods_.CATEGORY);//case2
        criteria.select(root);
        Query<Goods> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
