package com.cafe.dao;

import com.cafe.api.dao.ICategoryDao;
import com.cafe.model.Category;
import com.cafe.model.Category_;
import com.cafe.model.GenericEntity;
import com.cafe.model.Goods_;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryDao extends AbstractDao<Category> implements ICategoryDao {


    public CategoryDao() {
        super(Category.class);
    }

    @Override
    public List<Category> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        Root<Category> root = criteria.from(Category.class);
        root.fetch(Category_.parentCategory);
        criteria.select(root);
        Query<Category> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
