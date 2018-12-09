package com.cafe.dao;

import com.cafe.api.dao.IUserDao;
import com.cafe.model.Login;
import com.cafe.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@EnableTransactionManagement
@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {


    @Override
    public Class<User> getChildClass() {
        return User.class;
    }

    @Override
    public User getByLogin(Login login) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root)
                .where(builder.equal(root.get("login"), login.getLogin()))
                .where(builder.equal(root.get("password"), login.getPassword()));
        Query<User> query = session.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public User getByNameLogin(String nameLogin) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root)
                .where(builder.equal(root.get("login"), nameLogin));
        Query<User> query = session.createQuery(criteria);
        return query.getSingleResult();
    }
}
