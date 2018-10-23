package com.senla.services;

import com.senla.api.services.IServiceExit;
import com.senla.hibernate.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceExit implements IServiceExit {

    @Override
    public void closeSessionFactory() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.close();
    }
}
