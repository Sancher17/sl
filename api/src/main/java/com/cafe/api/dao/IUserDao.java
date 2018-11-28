package com.cafe.api.dao;

import com.cafe.model.Login;
import com.cafe.model.User;

public interface IUserDao extends GenericDao<User> {

    User validateUser(Login login);
}
