package com.cafe.services;

import com.cafe.api.dao.INameGoodsDao;
import com.cafe.api.services.INameGoodsService;
import com.cafe.model.NameGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NameGoodsService implements INameGoodsService {

    @Autowired
    INameGoodsDao nameGoodsDao;

    @Override
    public void add(NameGoods nameGoods) {

    }

    @Override
    public void update(NameGoods nameGoods) {

    }

    @Override
    public void delete(NameGoods nameGoods) {

    }

    @Override
    public NameGoods getById(Long id) {
        return null;
    }

    @Override
    public List<NameGoods> getAll() {
        return nameGoodsDao.getAll(NameGoods.class);
    }
}
