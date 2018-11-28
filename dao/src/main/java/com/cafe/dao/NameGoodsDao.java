package com.cafe.dao;

import com.cafe.api.dao.INameGoodsDao;
import com.cafe.model.NameGoods;

import java.util.List;

public class NameGoodsDao extends AbstractDao<NameGoods> implements INameGoodsDao {

    public NameGoodsDao() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }


}
