package com.cafe.api.services;

import com.cafe.dto.user.UserDto;

public interface IJwtService {

    String tokenFor(UserDto userDto);

    UserDto verify(String token);
}



