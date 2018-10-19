package com.senla.services.impl;

import com.senla.di.DependencyInjection;
import com.senla.fileworker.startModule.IFileWorker;
import com.senla.hibernate.IRequestDao;
import com.senla.hibernate.util.HibernateUtil;
import com.senla.services.IServiceRequest;
import entities.Request;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

import static com.senla.constants.Constants.PATH_REQUEST_CSV;

public class ServiceRequest extends Service implements IServiceRequest {

    private static final Logger log = Logger.getLogger(ServiceRequest.class);

    private static final String ADDED_REQUEST = "Добавлен запрос на книгу: ";
    private static final String REQUESTS_SORTED_BY_QUANTITY = "Запросы отсортированы по количеству запросов";
    private static final String REQUESTS_SORTED_BY_ALPHABET = "Запросы отсортированы по алфавиту";

    private IRequestDao requestDao;
    private IFileWorker fileWorker;

    public ServiceRequest() {
        this.requestDao = DependencyInjection.getBean(IRequestDao.class);
        this.fileWorker = DependencyInjection.getBean(IFileWorker.class);
    }

    @Override
    public void addBookRequest(Request request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean exist = false;
        try {
            session.beginTransaction();
            for (Request aRequest : requestDao.getAll(session, Request.class)) {
                if (aRequest != null) {
                    if (aRequest.getRequireNameBook().equals(request.getRequireNameBook())) {
                        exist = true;
                        aRequest.setRequireQuantity(aRequest.getRequireQuantity() + 1);
                        requestDao.update(session, aRequest);
                    }
                }
            }
            if (!exist) {
                request.setRequireQuantity(1);
                requestDao.add(session, request);
            }
            session.getTransaction().commit();
            notifyObservers(ADDED_REQUEST + request.getRequireNameBook());
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
    public List<Request> getRequestsSortedByQuantity() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            List<Request> requests = requestDao.getSortedByQuantity(session);
            notifyObservers(REQUESTS_SORTED_BY_QUANTITY);
            return requests;
        }  catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Request> getRequestsSortedByAlphabet() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            List<Request> requests = requestDao.getSortedByAlphabet(session);
            notifyObservers(REQUESTS_SORTED_BY_ALPHABET);
            return requests;
        }  catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Request> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return requestDao.getAll(session, Request.class);
        }  catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Request> getCompletedRequests() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return requestDao.getCompleted(session);
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @Override
    public List<Request> getNotCompletedRequests() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return requestDao.getNotCompleted(session);
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + e);
            notifyObservers(NO_DATA_FROM_BD);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void exportToCsv() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            super.writeToCsv(requestDao.getAll(session, Request.class));
        } catch (HibernateException e) {
            log.error(NO_DATA_FROM_BD + " / " + CAN_NOT_WRITE_DATA_TO_FILE + e);
            notifyObservers(NO_DATA_FROM_BD + " / " + CAN_NOT_WRITE_DATA_TO_FILE);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Request> importListFromFile = fileWorker.importListFromFile(PATH_REQUEST_CSV, session, Request.class);
            notifyObservers(PATH_REQUEST_CSV);
            merge(session, importListFromFile, requestDao, Request.class);
        } catch (Exception e) {
            log.error(CAN_NOT_ADD_DATA_FROM_FILE + e);
            notifyObservers(CAN_NOT_ADD_DATA_FROM_FILE);
        }
    }
}
