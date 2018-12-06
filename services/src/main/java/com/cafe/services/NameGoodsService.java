package com.cafe.services;

import com.cafe.api.dao.IGenericDao;
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

    private IGenericDao<NameGoods> getDao(){
        return nameGoodsDao;
    }

    @Override
    public void add(NameGoods nameGoods) {
        getDao().add(nameGoods);
    }

    @Override
    public void update(NameGoods nameGoods) {
        getDao().update(nameGoods);
    }

    @Override
    public void delete(Long id) {
        getDao().delete(id);
    }

    @Override
    public NameGoods getById(Long id) {
        return getDao().getById(id);
    }

    @Override
    public List<NameGoods> getAll() {
        return getDao().getAll();
    }

    @Override
    public NameGoods getByName(String name) {
        return getDao().getByName(name);
    }
}
