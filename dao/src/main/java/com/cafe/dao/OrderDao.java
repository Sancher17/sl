package com.cafe.dao;

import com.cafe.api.dao.IOrderDao;
import com.cafe.model.*;
import com.cafe.model.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.FetchType;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    protected OrderDao() {
        super(Order.class);
    }

    @Override
    public List<Order> getListByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = criteria.from(Order.class);
        criteria.select(root)
                .where(builder.greaterThan(root.get(Order_.CREATED), startDate))
                .where(builder.lessThan(root.get(Order_.CREATED), endDate));
        Query<Order> query = session.createQuery(criteria);
        return query.getResultList();
    }


    // TODO: 11.12.2018  - не то возвращает, если не решу то удалить всю цепочку
    @Override
    public List<Order> getListGoodsById(Long id) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root =  criteria.from(Order.class);
        root.fetch(Order_.user);
        root.fetch(Order_.goods);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Goods_.id), id));
        Query<Order> query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Order> getListOrdersForUser(Long id){
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = getOrderRoot(criteria);
        criteria.select(root);
        criteria.where(builder.equal(root.get(User_.id), id));
        Query<Order> query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Order> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = getOrderRoot(criteria);
        criteria.select(root);
        Query<Order> query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Order getById(Long id) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> root = getOrderRoot(criteria);
        criteria.where(builder.equal(root.get(Order_.ID), id));
        Query<Order> query = getSession().createQuery(criteria);
        return query.getSingleResult();
    }

    private Root<Order> getOrderRoot(CriteriaQuery<Order> criteria) {
        Root<Order> root = criteria.from(Order.class);
        root.fetch(Order_.user);
        Fetch<Order, Goods> goods = root.fetch(Order_.goods);
        goods.fetch(Goods_.nameGoods);
        goods.fetch(Goods_.category);
        return root;
    }
}
