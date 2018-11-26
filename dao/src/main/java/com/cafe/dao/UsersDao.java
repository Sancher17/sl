package com.cafe.dao;

import com.cafe.api.dao.IUsersDao;
import com.cafe.model.Login;
import com.cafe.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UsersDao extends AbstractDao<User> implements IUsersDao {

    public UsersDao() {
        System.out.println(this.getClass().getSimpleName() + " -- constructor");
    }

    @Override
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


//        String sql = "select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword();
//        List<User> users = jdbcTemplate.query(sql, new UserMapper());
//        return users.size() > 0 ? users.get(0) : null;
    }
}
