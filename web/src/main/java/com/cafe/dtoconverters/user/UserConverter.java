package com.cafe.dtoconverters.user;

import com.cafe.api.dtoconverters.IUserConverter;
import com.cafe.dto.user.UserDto;
import com.cafe.dto.user.UserFullDataDto;
import com.cafe.model.User;
import com.cafe.utils.DateUtil;

public class UserConverter implements IUserConverter {

    @Override
    public User toModel(UserDto dto) {
        return null;
    }

    @Override
    public UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        if (entity != null){
            userDto.setId(entity.getId());
            userDto.setFirstName(entity.getFirstName());
            userDto.setLastName(entity.getLastName());
            userDto.setType(entity.getUserType());
            userDto.setLogin(entity.getLogin());
            userDto.setPassword(entity.getPassword());
            return userDto;
        }
        return null;

    }

    @Override
    public User updateEntity(User entity, UserDto dto) {
        return null;
    }

    @Override
    public User toModelFull(UserFullDataDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDateBirthday(DateUtil.dateFromString(userDto.getDatBirthday()));
        user.setUserType(userDto.getType());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        return user;
    }
}

