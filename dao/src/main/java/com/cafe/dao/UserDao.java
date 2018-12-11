package com.cafe.dao;

import com.cafe.api.dao.IUserDao;
import com.cafe.model.Login;
import com.cafe.model.User;
import com.cafe.model.User_;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {

    public static final String NO_ENTITY_WITH_SUCH_CRITERIA = "нет записи в базе с такими параметрами ";
    private static final Logger log = Logger.getLogger(UserDao.class);


    protected UserDao(){
        super(User.class);
    }

    @Override
    public User getByLogin(Login login) {
        try {
            Session session = getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root)
                    .where(builder.and(
                            builder.equal(root.get(User_.login), login.getLogin()),
                            builder.equal(root.get(User_.PASSWORD), login.getPassword())));
            Query<User> query = session.createQuery(criteria);
            return query.getSingleResult();
        }catch (Exception e){
            log.info(NO_ENTITY_WITH_SUCH_CRITERIA + e);
            return null;
        }
    }

    @Override
    public User getByNameLogin(String nameLogin) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root)
                .where(builder.equal(root.get(User_.LOGIN), nameLogin));
        Query<User> query = session.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public User getByName(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(builder.equal(root.get(User_.FIRST_NAME), name));
        Query<User> query = getSession().createQuery(criteria);
        return query.getSingleResult();
    }
}
