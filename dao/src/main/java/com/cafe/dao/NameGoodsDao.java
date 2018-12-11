package com.cafe.dao;

import com.cafe.api.dao.INameGoodsDao;
import com.cafe.model.GenericEntity;
import com.cafe.model.NameGoods;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class NameGoodsDao extends AbstractDao<NameGoods> implements INameGoodsDao {

    protected NameGoodsDao() {
        super(NameGoods.class);
    }
}
