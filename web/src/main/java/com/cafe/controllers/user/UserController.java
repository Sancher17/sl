package com.cafe.controllers.user;

import com.cafe.api.services.IUserService;
import com.cafe.dto.user.UserDto;
import com.cafe.dto.user.UserFullDataDto;
import com.cafe.model.User;
import com.cafe.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/")
    public List<UserDto> getAll() {
        return userService.getAll()
                .stream().map(UserDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/")
    public List<UserFullDataDto> getAllData() {
        return userService.getAll().stream()
                .map(UserFullDataDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public UserDto getById(@PathVariable("id") Long id) {
        return new UserDto(userService.getById(id));
    }

    @PutMapping
    public void create(@RequestBody UserFullDataDto userDto) {
        userService.add(dtoToModel(userDto));
    }

    @PostMapping(value = "/{id}")
    public void update(@RequestBody UserFullDataDto userDto, @PathVariable("id") Long id) {
        User user = userService.getById(id);
        user = dtoToModel(userDto);
        userService.update(user);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    private User dtoToModel(UserFullDataDto userDto) {
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
