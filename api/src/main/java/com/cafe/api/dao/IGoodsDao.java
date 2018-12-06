package com.cafe.api.dao;

import com.cafe.model.Goods;

import java.util.List;

public interface IGoodsDao extends IGenericDao<Goods> {

    List<Goods> getListByName(String name);
}
