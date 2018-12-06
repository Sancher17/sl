package com.cafe.dao;

import com.cafe.api.dao.IOrderDao;
import com.cafe.model.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    public static final String DATE_OF_CREATED = "created";

    @Override
    public Class<Order> getChildClass() {
        return Order.class;
    }

    @Override
    public List<Order> getListByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(getChildClass());
        Root<Order> root = criteria.from(getChildClass());
        criteria.select(root)
                .where(builder.greaterThan(root.get(DATE_OF_CREATED), startDate))
                .where(builder.lessThan(root.get(DATE_OF_CREATED), endDate));
        Query<Order> query = session.createQuery(criteria);
        return query.getResultList();
    }
}
