package com.senla.services.impl;

import com.senla.hibernate.util.HibernateUtil;
import com.senla.services.IServiceExit;
import org.hibernate.SessionFactory;

public class ServiceExit implements IServiceExit {

    @Override
    public void closeSessionFactory() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.close();
    }
}
