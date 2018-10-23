package com.senla.hibernate;

import com.senla.api.dao.IRequestDao;
import entities.Request;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class RequestDao implements IRequestDao {

    private static final String REQUIRE_QUANTITY = "requireQuantity";
    private static final String REQUIRE_NAME_BOOK = "requireNameBook";
    private static final String REQUIRE_IS_COMPLETED = "requireIsCompleted";

    @Override
    public List<Request> getSortedByQuantity(Session session) {
        return getRequestsSortedByOrder(session, REQUIRE_QUANTITY);
    }

    @Override
    public List<Request> getSortedByAlphabet(Session session) {
        return getRequestsSortedByOrder(session, REQUIRE_NAME_BOOK);
    }

    @Override
    public List<Request> getCompleted(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Request> criteria = builder.createQuery(Request.class);
        Root<Request> root = criteria.from(Request.class);
        criteria.select(root)
                .where(builder.isTrue(root.get(REQUIRE_IS_COMPLETED)));
        Query<Request> query = session.createQuery(criteria);
        return query.list();
    }

    @Override
    public List<Request> getNotCompleted(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Request> criteria = builder.createQuery(Request.class);
        Root<Request> root = criteria.from(Request.class);
        criteria.select(root)
                .where(builder.isFalse(root.get(REQUIRE_IS_COMPLETED)));
        Query<Request> query = session.createQuery(criteria);
        return query.list();
    }

    private List<Request> getRequestsSortedByOrder(Session session, String order) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Request> criteria = builder.createQuery(Request.class);
        Root<Request> root = criteria.from(Request.class);
        criteria.select(root).orderBy(builder.asc(root.get(order)));
        Query<Request> query = session.createQuery(criteria);
        return query.list();
    }
}