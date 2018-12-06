package com.cafe.dao;

import com.cafe.api.dao.IUserDao;
import com.cafe.model.GenericEntity;
import com.cafe.model.Login;
import com.cafe.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@EnableTransactionManagement
@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {


    @Override
    public Class<User> getChildClass() {
        return User.class;
    }

    // TODO: 02.12.2018 удалить
    public User validateUser(Login login) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root)
                .where(builder.isTrue(root.get(login.getUsername())))
                .where(builder.isTrue(root.get(login.getPassword())));
        Query<User> query = session.createQuery(criteria);
        List<User> users = query.list();
        return users.get(0);
    }
}
