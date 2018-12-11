package com.cafe.dao;

import com.cafe.api.dao.IGenericDao;
import com.cafe.model.GenericEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public abstract class AbstractDao<T extends GenericEntity> implements IGenericDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class clazz;

   protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public AbstractDao(Class clazz){
       this.clazz=clazz;
    }

    @Override
    public void add(T t) {
        getSession().save(t);
    }

    @Override
    public void delete(Long id) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaDelete<T> query = builder.createCriteriaDelete(clazz);
        Root<T> root =query.from(clazz);
        query.where(builder.equal(root.get("id"), id));
        getSession().createQuery(query).executeUpdate();
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public T getById(Long id) {
        return (T) getSession().get(clazz, id);
    }

    @Override
    public T getByName(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.where(builder.equal(root.get("name"), name));
        Query<T> query = getSession().createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public List<T> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.select(root);
        TypedQuery<T> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
