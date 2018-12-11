package com.cafe.dao;

import com.cafe.api.dao.ICategoryDao;
import com.cafe.model.Category;
import com.cafe.model.Category_;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryDao extends AbstractDao<Category> implements ICategoryDao {

    protected CategoryDao() {
        super(Category.class);
    }

    @Override
    public List<Category> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        Root<Category> root = getCategoryRoot(criteria);
        criteria.select(root);
        Query<Category> query = session.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Category getById(Long id) {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        Root<Category> root = getCategoryRoot(criteria);
        criteria.where(builder.equal(root.get(Category_.ID), id));
        Query<Category> query = getSession().createQuery(criteria);
        return query.getSingleResult();
    }


    private Root<Category> getCategoryRoot(CriteriaQuery<Category> criteria) {
        Root<Category> root = criteria.from(Category.class);
        root.fetch(Category_.parentCategory);
        criteria.select(root);
        return root;
    }
}
