package com.senla.services.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.hibernate.IBookDao;
import com.senla.hibernate.IRequestDao;
import com.senla.hibernate.util.HibernateUtil;
import com.senla.services.IServiceBook;
import com.senla.util.date.DateUtil;
import entities.Book;
import entities.Request;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

import static com.senla.constants.Constants.ALLOW_MARK_REQUESTS;
import static com.senla.constants.Constants.BOOK_IS_OLD;
import static com.senla.constants.Constants.PATH_BOOK_CSV;


public class ServiceBook extends Service implements IServiceBook {

    private static final Logger log = Logger.getLogger(ServiceBook.class);

    private static final String BOOK_ADDED = "Добавлена книга: ";
    private static final String BOOK_DELETED = "Удалена книга: ";
    private static final String NO_BOOK_WITH_SUCH_INDEX = "Книги с таким индексом нет !!!\n";
    private static final String BOOKS_SORTED_BY_ALPHABET = "Книги отсортированы по алфавиту\n";
    private static final String BOOKS_SORTED_BY_DATE_OF_PUBLICATION = "Книги отсортированы по дате публикации\n";
    private static final String BOOKS_SORTED_BY_PRICE = "Книги отсортированы по цене\n";
    private static final String BOOKS_SORTED_BY_AVAILABILITY = "Книги отсортированы по доступности\n";
    private static final String BOOKS_ADDED_TO_STORE_MORE_SIX_MONTH_SORTED_BY_DATE = "Книги добавленные в магазин более 6 месяцев назад, сортировка по дате\n";
    private static final String BOOKS_ADDED_TO_STORE_MORE_SIX_MONTH_SORTED_BY_PRICE = "Книги добавленные в магазин более 6 месяцев назад, сортировка по цене\n";
    public static final String CAN_NOT_WRITE_DATA_TO_FILE = "Не удачная запись данны в файл";
    public static final String CAN_NOT_ADD_DATA_FROM_FILE = "Не удачная попытка добавить данные из файла ";

    private IBookDao bookDao;
    private IRequestDao requestDao;
    private IFileWorker fileWorker;

    public ServiceBook() {
        this.bookDao = DependencyInjection.getBean(IBookDao.class);
        this.requestDao = DependencyInjection.getBean(IRequestDao.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addBook(Book book) {
        /**с сайта Hibernate*/
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.save( new Event( "Our very first event!", new Date() ) );
//        session.save( new Event( "A follow up event", new Date() ) );
//        session.getTransaction().commit();
//        session.close();
        /***/

        Session session = HibernateUtil.getSessionFactory().openSession();
        notifyObservers(BOOK_ADDED + book.getNameBook());
        try {
            session.beginTransaction();
            bookDao.add(session, book);
            if (ALLOW_MARK_REQUESTS) {
                List<Request> requests = requestDao.getAll(session);
                for (Request request : requests) {
                    if (book.getNameBook().equals(request.getRequireNameBook())) {
                        request.setRequireIsCompleted(true);
                        requestDao.update(session, request);
                    }
                }
            }
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            log.error(CAN_NOT_ADD_DATA_TO_BD + e);
            session.getTransaction().rollback();
            session.close();
        }
    }

    @Override
    public void deleteBookById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Book book = bookDao.getById(session, id);
            if (book != null) {
                notifyObservers(BOOK_DELETED + book);
                bookDao.deleteById(session, id);
            } else {
                notifyObservers(NO_BOOK_WITH_SUCH_INDEX);
            }
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
            session.getTransaction().rollback();
            session.close();
        }
    }

    @Override
    public List<Book> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Book> books = bookDao.getAll(session);
            session.close();
            return books;
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
            session.close();
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByAlphabet() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        notifyObservers(BOOKS_SORTED_BY_ALPHABET);
        try {
            List<Book> books = bookDao.getSortedByAlphabet(session);
            session.close();
            return books;
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
            session.close();
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByDatePublication() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        notifyObservers(BOOKS_SORTED_BY_DATE_OF_PUBLICATION);
        try {
            List<Book> books = bookDao.getSortedByDatePublication(session);
            session.close();
            return books;
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
            session.close();
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByPrice() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        notifyObservers(BOOKS_SORTED_BY_PRICE);
        try {
            List<Book> books = bookDao.getSortedByPrice(session);
            session.close();
            return books;
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
            session.close();
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByAvailability() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        notifyObservers(BOOKS_SORTED_BY_AVAILABILITY);
        try {
            List<Book> books = bookDao.getSortedByAvailability(session);
            session.close();
            return books;
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
            session.close();
        }
        return null;
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        notifyObservers(BOOKS_ADDED_TO_STORE_MORE_SIX_MONTH_SORTED_BY_DATE);
        try {
            List<Book> books = bookDao.getPeriodMoreSixMonthByDate(session);
            session.close();
            return books;
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
            session.close();
        }
        return null;
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByPrice() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        notifyObservers(BOOKS_ADDED_TO_STORE_MORE_SIX_MONTH_SORTED_BY_PRICE);
        try {
            List<Book> books = bookDao.getPeriodMoreSixMonthByPrice(session);
            session.close();
            return books;
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
            session.close();
        }
        return null;
    }

    @Override
    public String getBookDescriptionById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Book book = bookDao.getById(session, id);
            if (book != null) {
                return book.getDescription();
            }
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public Book getBookById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Book book = bookDao.getById(session, id);
            session.close();
            return book;
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;

    }

    @Override
    public void markBookOld() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (BOOK_IS_OLD != null) {
            Date periodOfMonth = DateUtil.minusMonths(BOOK_IS_OLD);
            try {
                session.beginTransaction();
                List<Book> books = bookDao.getNewBooks(session, periodOfMonth);
                for (Book book : books) {
                    book.setOld(true);
                    bookDao.update(session, book);
                }
                session.getTransaction().commit();
                session.close();
            } catch (HibernateException e) {
                log.error(NO_DATA_FROM_BD + e);
                notifyObservers(NO_DATA_FROM_BD);
                session.getTransaction().rollback();
                session.close();
            }
        }
    }

    @Override
    public void exportToCsv() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            super.writeToCsv(bookDao.getAll(session));
            session.close();
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + " / " + CAN_NOT_WRITE_DATA_TO_FILE + e);
            notifyObservers(NO_DATA_FROM_BD + " / " + CAN_NOT_WRITE_DATA_TO_FILE);
        }
    }

    @Override
    public void importFromCsv() {
        List<Book> importListFromFile = fileWorker.importListFromFile(PATH_BOOK_CSV, Book.class);
        notifyObservers(PATH_BOOK_CSV);
        try {
            merge(importListFromFile, bookDao);
        } catch (Exception e) {
            log.error(CAN_NOT_ADD_DATA_FROM_FILE + e);
        }
    }
}
