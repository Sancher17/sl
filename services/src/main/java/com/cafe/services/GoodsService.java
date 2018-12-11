package com.cafe.services;

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


    @Override
    public void add(Goods goods) {
        goodsDao.add(goods);
    }

    @Override
    public void update(Goods goods) {
        goodsDao.update(goods);
    }

    @Override
    public void delete(Long id) {
        goodsDao.delete(id);
    }

    @Override
    public Goods getById(Long id) {
        return goodsDao.getById(id);
    }

    @Override
    public List<Goods> getAll() {
        return goodsDao.getAll();
    }

    @Override
    public Goods getByName(String name) {
        return null;
    }




}
