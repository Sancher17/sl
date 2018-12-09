package com.cafe.security.service;

import com.cafe.api.services.IUserService;
import com.cafe.dto.user.UserDto;
import com.cafe.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService {

    @Autowired
    private IUserService userService;

    public UserDto login(Login login) {
        return new UserDto(userService.getByLogin(login));
    }
}
