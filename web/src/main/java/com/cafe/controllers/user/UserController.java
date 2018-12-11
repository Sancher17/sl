package com.cafe.controllers.user;

import com.cafe.api.services.IUserService;
import com.cafe.dto.user.UserDto;
import com.cafe.dto.user.UserFullDataDto;
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

    @PostMapping(value = "/all")
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
    public void create(@RequestBody UserDto userDto) {
        userService.add(userDto.toModel());
    }

    @PostMapping(value = "/")
    public void update(@RequestBody UserFullDataDto userDto) {
        userService.update(userDto.toModelFull());
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
