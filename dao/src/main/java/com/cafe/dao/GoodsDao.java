package com.cafe.dao;

import com.cafe.api.dao.IGoodsDao;
import com.cafe.dao.util.HibernateUtil;
import com.cafe.model.Goods;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsDao extends AbstractDao<Goods> implements IGoodsDao{

    public GoodsDao() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }
}
