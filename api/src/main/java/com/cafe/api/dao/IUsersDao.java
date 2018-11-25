package com.cafe.api.dao;

import com.cafe.model.Login;
import com.cafe.model.User;

public interface IUsersDao extends GenericDao<User> {

    User validateUser(Login login);
}
