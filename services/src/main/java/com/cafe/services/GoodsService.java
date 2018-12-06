package com.cafe.services;

import com.cafe.api.dao.IGenericDao;
import com.cafe.api.dao.IGoodsDao;
import com.cafe.api.services.ICategoryService;
import com.cafe.api.services.IGoodsService;
import com.cafe.api.services.INameGoodsService;
import com.cafe.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsService implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    @Autowired
    private INameGoodsService nameGoodsService;

    @Autowired
    private ICategoryService categoryService;

    private IGenericDao<Goods> getDao() {
        return goodsDao;
    }

    @Override
    public void add(Goods goods) {
        getDao().add(goods);
    }

    @Override
    public void update(Goods goods) {
        getDao().update(goods);
    }

    @Override
    public void delete(Long id) {
        getDao().delete(id);
    }

    @Override
    public Goods getById(Long id) {
        return getDao().getById(id);
    }

    @Override
    public List<Goods> getAll() {
        return getDao().getAll();
    }

    @Override
    public Goods getByName(String name) {
        return null;
    }




}
