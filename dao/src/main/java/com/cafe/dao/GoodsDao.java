package com.cafe.dao;

import com.cafe.api.dao.IGoodsDao;
import com.cafe.dao.util.HibernateUtil;
import com.cafe.model.GenericEntity;
import com.cafe.model.Goods;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GoodsDao extends AbstractDao<Goods> implements IGoodsDao {

    @Override
    public Class<Goods> getChildClass() {
        return Goods.class;
    }

    @Override
    public List<Goods> getListByName(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Goods> criteria = builder.createQuery(getChildClass());
        Root<Goods> root = criteria.from(getChildClass());
        criteria.where(builder.equal(root.get("name"), name));
        Query<Goods> query = getSession().createQuery(criteria);
        return query.getResultList();
    }
}
