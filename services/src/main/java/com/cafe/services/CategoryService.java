package com.cafe.services;

import com.cafe.api.dao.ICategoryDao;
import com.cafe.api.dao.IGenericDao;
import com.cafe.api.services.ICategoryService;
import com.cafe.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    private IGenericDao<Category> getDao() {
        return categoryDao;
    }

    @Override
    public void add(Category category) {
        getDao().add(category);
    }

    @Override
    public void update(Category category) {
        getDao().update(category);
    }

    @Override
    public void delete(Long id) {
        getDao().delete(id);
    }

    @Override
    public Category getById(Long id) {
        return getDao().getById(id);
    }

    @Override
    public List<Category> getAll() {
        return getDao().getAll();
    }

    @Override
    public Category getByName(String name) {
        return getDao().getByName(name);
    }

}
