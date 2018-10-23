package com.senla.services;

import com.senla.api.dao.IBookDao;
import com.senla.api.dao.IRequestDao;
import com.senla.api.fileworker.IFileWorker;
import com.senla.api.services.IServiceBook;
import com.senla.hibernate.util.HibernateUtil;
import com.senla.util.DateUtil;
import entities.Book;
import entities.Request;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.senla.constants.Constants.*;

@Component
public class ServiceBook extends Service implements IServiceBook {

    private static final Logger log = Logger.getLogger(ServiceBook.class);

    private static final String BOOK_ADDED = "Добавлена книга: ";
    private static final String BOOK_DELETED = "Удалена книга: ";
    private static final String NO_BOOK_WITH_SUCH_INDEX = "Книги с таким индексом нет !!!\n";
    private static final String BOOKS_SORTED_BY_ALPHABET = "Книги отсортированы по алфавиту\n";
    private static final String BOOKS_SORTED_BY_DATE_OF_PUBLICATION = "Книги отсортированы по дате публикации\n";
    private static final String BOOKS_SORTED_BY_PRICE = "Книги отсортированы по цене\n";
    private static final String BOOKS_SORTED_BY_AVAILABILITY = "Книги отсортированы по доступности\n";

    private IBookDao bookDao;
    private IRequestDao requestDao;
    private IFileWorker fileWorker;

    public ServiceBook(IBookDao bookDao, IRequestDao requestDao, IFileWorker fileWorker) {
        this.bookDao = bookDao;
        this.requestDao = requestDao;
        this.fileWorker = fileWorker;
    }

    @Override
    public void addBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            bookDao.add(session, book);
            if (ALLOW_MARK_REQUESTS) {
                List<Request> requests = requestDao.getAll(session, Request.class);
                for (Request request : requests) {
                    if (book.getNameBook().equals(request.getRequireNameBook())) {
                        request.setRequireIsCompleted(true);
                        requestDao.update(session, request);
                    }
                }
            }
            session.getTransaction().commit();
            notifyObservers(BOOK_ADDED + book.getNameBook());
        } catch (Exception e) {
            log.error(CAN_NOT_ADD_DATA_TO_BD + e);
            notifyObservers(CAN_NOT_ADD_DATA_TO_BD);
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteBookById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Book book = bookDao.getById(session, id, Book.class);
            if (book != null) {
                bookDao.delete(session, book);
            } else {
                notifyObservers(NO_BOOK_WITH_SUCH_INDEX);
            }
            session.getTransaction().commit();
            notifyObservers(BOOK_DELETED + book);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return bookDao.getAll(session, Book.class);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByAlphabet() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Book> books = bookDao.getSortedByAlphabet(session);
            notifyObservers(BOOKS_SORTED_BY_ALPHABET);
            return books;
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByDatePublication() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Book> books = bookDao.getSortedByDatePublication(session);
            notifyObservers(BOOKS_SORTED_BY_DATE_OF_PUBLICATION);
            return books;
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByPrice() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Book> books = bookDao.getSortedByPrice(session);
            notifyObservers(BOOKS_SORTED_BY_PRICE);
            return books;
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByAvailability() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Book> books = bookDao.getSortedByAvailability(session);
            notifyObservers(BOOKS_SORTED_BY_AVAILABILITY);
            return books;
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByDate() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return bookDao.getPeriodMoreSixMonthByDate(session);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Book> getBooksPeriodMoreSixMonthByPrice() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return bookDao.getPeriodMoreSixMonthByPrice(session);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public String getBookDescriptionById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Book book = bookDao.getById(session, id, Book.class);
            if (book != null) {
                return book.getDescription();
            }
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public Book getBookById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return bookDao.getById(session, id, Book.class);
        } catch (Exception e) {
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
                session.getTransaction().begin();
                List<Book> books = bookDao.getNewBooks(session, periodOfMonth);
                for (Book book : books) {
                    book.setOld(true);
                    bookDao.update(session, book);
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                log.error(NO_DATA_FROM_BD + e);
                notifyObservers(NO_DATA_FROM_BD);
                if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                        || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                    session.getTransaction().rollback();
                }
            } finally {
                session.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void exportToCsv() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            super.writeToCsv(bookDao.getAll(session, Book.class), fileWorker);
        } catch (Exception e) {
            log.error(NO_DATA_FROM_BD + " / " + CAN_NOT_WRITE_DATA_TO_FILE + e);
            notifyObservers(NO_DATA_FROM_BD + " / " + CAN_NOT_WRITE_DATA_TO_FILE);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Book> importListFromFile = fileWorker.importListFromFile(PATH_BOOK_CSV, session, Book.class, bookDao);
            notifyObservers(PATH_BOOK_CSV);
            merge(session, importListFromFile, bookDao, Book.class);
        } catch (Exception e) {
            log.error(CAN_NOT_ADD_DATA_FROM_FILE + e);
            notifyObservers(CAN_NOT_ADD_DATA_FROM_FILE);
        }
    }
}
