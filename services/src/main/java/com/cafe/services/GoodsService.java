package com.cafe.services;

import com.cafe.api.dao.IGoodsDao;
import com.cafe.api.services.IGoodsService;
import com.cafe.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GoodsService implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    public GoodsService() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }

    @Transactional
    @Override
    public void add(Goods goods) {

    }

    @Transactional
    @Override
    public void update(Goods goods) {

    }

    @Transactional
    @Override
    public void delete(Goods goods) {

    }

    @Transactional
    @Override
    public Goods getById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public List<Goods> getAll() {
        return goodsDao.getAll(Goods.class);
    }
}
