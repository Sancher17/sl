package com.cafe.services;

import com.cafe.api.dao.IGoodsDao;
import com.cafe.api.services.IGoodsService;
import com.cafe.dao.util.HibernateUtil;
import com.cafe.model.Goods;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsService implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    public GoodsService() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }

    @Override
    public List<Goods> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return goodsDao.getAll(session, Goods.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
