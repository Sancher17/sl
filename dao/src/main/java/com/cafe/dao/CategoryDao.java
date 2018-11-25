package com.cafe.dao;

import com.cafe.api.dao.ICategoryDao;
import com.cafe.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao extends AbstractDao<Category> implements ICategoryDao {
}
