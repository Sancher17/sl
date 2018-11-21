package com.cafe.services;

import com.cafe.api.dao.IGoodsDao;
import com.cafe.api.services.IGoodsService;
import com.cafe.dao.GoodsDao;
import com.cafe.dao.util.HibernateUtil;
import com.cafe.model.Goods;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iGoodsService")
public class GoodsService implements IGoodsService {

    @Autowired
    private GoodsDao goodsDao = new GoodsDao();

    @Override
    public List<Goods> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return goodsDao.getAll(session, Goods.class);
        } catch (Exception e) {
//            log.error(NO_DATA_FROM_BD + e);
            e.printStackTrace();
        }
        return null;
    }
}
