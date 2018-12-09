package com.cafe.security.service;

import com.cafe.api.services.IUserService;
import com.cafe.dto.user.UserDto;
import com.cafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProfileService {

    @Autowired
    IUserService userService;


    protected Optional<User> get(String nameLogin) {
        return Optional.ofNullable(userService.getByNameLogin(nameLogin));
    }

    public Optional<UserDto> minimal(String login) {
        return get(login).map(UserDto::new);
    }

}
