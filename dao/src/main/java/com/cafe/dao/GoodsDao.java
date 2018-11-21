package com.cafe.dao;

import com.cafe.api.dao.IGoodsDao;
import com.cafe.model.Goods;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("iGoodsDao")
public class GoodsDao implements IGoodsDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Goods> getAllCountries() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Goods> countryList = session.createQuery("from Country").list();
        return countryList;
    }





}
