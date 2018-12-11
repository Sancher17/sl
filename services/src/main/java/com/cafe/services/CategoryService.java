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


    @Override
    public void add(Category category) {
        categoryDao.add(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }

    @Override
    public Category getById(Long id) {
        return categoryDao.getById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public Category getByName(String name) {
        return categoryDao.getByName(name);
    }

}
