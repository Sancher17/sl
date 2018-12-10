package com.cafe.controllers.login;

import com.cafe.api.dtoconverters.IUserConverter;
import com.cafe.api.services.IUserService;
import com.cafe.dto.user.UserDto;
import com.cafe.model.Login;
import com.cafe.security.exceptions.FailedToLoginException;
import com.cafe.services.JwtService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private IUserConverter userConverter;

    @PostMapping()
    public String  login(@RequestBody Login login, HttpServletResponse response) {
        UserDto userDto = userConverter.toDto(userService.getByLogin(login));
        if (userDto != null) {
            String token = jwtService.tokenFor(userDto);
            response.setHeader("Token", token);
            return String.format("Пользователь %s %s залогировался", userDto.getFirstName(), userDto.getLastName());
        }
        throw new FailedToLoginException(login.getLogin());
    }
}
