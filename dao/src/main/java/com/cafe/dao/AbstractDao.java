package com.cafe.dao;

import com.cafe.api.dao.IGenericDao;
import com.cafe.model.GenericEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AbstractDao<T extends GenericEntity> implements IGenericDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(T t) {
        getSession().save(t);
    }

    @Override
    public void delete(Long id) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaDelete<T> query = builder.createCriteriaDelete(getChildClass());
        Root<T> root =query.from(getChildClass());
        query.where(builder.equal(root.get("id"), id));
        getSession().createQuery(query).executeUpdate();
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public T getById(Long id) {
        return getSession().get(getChildClass(), id);
    }

    @Override
    public T getByName(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getChildClass());
        Root<T> root = criteria.from(getChildClass());
        criteria.where(builder.equal(root.get("name"), name));
        Query<T> query = getSession().createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public List<T> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getChildClass());
        Root<T> root = criteria.from(getChildClass());
        criteria.select(root);
        TypedQuery<T> query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Class<T> getChildClass() {
        return null;
    }
}
