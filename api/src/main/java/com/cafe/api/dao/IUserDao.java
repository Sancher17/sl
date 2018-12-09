package com.cafe.api.dao;

import com.cafe.model.Login;
import com.cafe.model.User;

public interface IUserDao extends IGenericDao<User> {

    User getByLogin(Login login);

    User getByNameLogin(String nameLogin);
}
