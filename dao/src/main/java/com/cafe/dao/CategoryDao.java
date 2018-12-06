package com.cafe.dao;

import com.cafe.api.dao.ICategoryDao;
import com.cafe.model.Category;
import com.cafe.model.GenericEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao extends AbstractDao<Category> implements ICategoryDao {

    @Override
    public Class<Category> getChildClass() {
        return Category.class;
    }

}
