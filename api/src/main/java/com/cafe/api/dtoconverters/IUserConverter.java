package com.cafe.api.dtoconverters;

import com.cafe.dto.user.UserDto;
import com.cafe.dto.user.UserFullDataDto;
import com.cafe.model.User;

public interface IUserConverter extends GenericConverter<User, UserDto> {

    User toModelFull(UserFullDataDto userDto);
}
