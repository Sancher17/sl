package com.cafe.dao;

import com.cafe.api.dao.IGoodsDao;
import com.cafe.dao.util.HibernateUtil;
import com.cafe.model.Goods;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class GoodsDao implements IGoodsDao {

    public GoodsDao() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }

    public List<Goods> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (List<Goods>) session.createQuery("from Goods ").list();
    }





}
