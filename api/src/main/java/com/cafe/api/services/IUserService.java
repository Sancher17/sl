package com.cafe.api.services;

import com.cafe.model.Login;
import com.cafe.model.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends IService<User>{

    User getByLogin(Login login);

    User getByNameLogin(String nameLogin);
}
