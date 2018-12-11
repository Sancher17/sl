package com.cafe.controllers.login;

import com.cafe.api.services.IJwtService;
import com.cafe.api.services.IUserService;
import com.cafe.dto.user.UserDto;
import com.cafe.model.Login;
import com.cafe.security.exceptions.FailedToLoginException;
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
    public static final String TOKEN = "Token";

    @Autowired
    private IUserService userService;
    @Autowired
    private IJwtService jwtService;


    @PostMapping()
    public String  login(@RequestBody Login login, HttpServletResponse response) {
        UserDto userDto = new UserDto(userService.getByLogin(login));
        if (userDto != null) {
            String token = jwtService.tokenFor(userDto);
            response.setHeader(TOKEN, token);
            return String.format("Пользователь %s %s залогировался", userDto.getFirstName(), userDto.getLastName());
        }
        throw new FailedToLoginException(login.getLogin());
    }
}
