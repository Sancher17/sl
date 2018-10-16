package com.test;

import com.senla.hibernate.IBookDao;
import com.senla.hibernate.impl.BookDao;
import com.senla.hibernate.util.HibernateUtil;
import entities.Book;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

public class TestHiber {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        IBookDao bookDao = new BookDao();
        Book book = bookDao.getById(session, 6L);
//        System.out.println(book);

//        List<Book> books = bookDao.getAll(session);
//        for (Book book1 : books) {
//            System.out.println(book1);
//        }

        try {
            Book book1 = bookDao.getByName(session, "Hoesy");
            System.out.println(book1);
        }catch (NoResultException e){
            System.out.println("no result");
        }
    }
}
