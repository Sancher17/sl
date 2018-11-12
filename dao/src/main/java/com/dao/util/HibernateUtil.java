package com.dao.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final String CAN_NOT_GET_HIBERNATE_CONFIGURATION = "Не удалось получить конфигурацию Hibernate ";

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .buildSessionFactory();
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
