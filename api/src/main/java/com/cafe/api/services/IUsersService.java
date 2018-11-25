package com.cafe.api.services;

import com.cafe.model.Login;
import com.cafe.model.User;
import org.springframework.stereotype.Service;

@Service
public interface IUsersService extends IService<User>{

    User validateUser(Login login);


}
