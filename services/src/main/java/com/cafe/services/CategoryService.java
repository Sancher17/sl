package com.cafe.services;

import com.cafe.api.dao.ICategoryDao;
import com.cafe.api.services.ICategoryService;
import com.cafe.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    public CategoryService() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }

    @Override
    public void add(Category category) {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll(Category.class);
    }
}
