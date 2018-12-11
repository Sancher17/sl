package com.cafe.services;

import com.cafe.api.dao.INameGoodsDao;
import com.cafe.api.services.INameGoodsService;
import com.cafe.model.NameGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class NameGoodsService implements INameGoodsService {

    @Autowired
    private INameGoodsDao nameGoodsDao;

    @Override
    public void add(NameGoods nameGoods) {
        nameGoodsDao.add(nameGoods);
    }

    @Override
    public void update(NameGoods nameGoods) {
        nameGoodsDao.update(nameGoods);
    }

    @Override
    public void delete(Long id) {
        nameGoodsDao.delete(id);
    }

    @Override
    public NameGoods getById(Long id) {
        return nameGoodsDao.getById(id);
    }

    @Override
    public List<NameGoods> getAll() {
        return nameGoodsDao.getAll();
    }

    @Override
    public NameGoods getByName(String name) {
        return nameGoodsDao.getByName(name);
    }
}
