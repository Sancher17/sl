package com.cafe.controllers.login;

import com.cafe.dto.user.UserDto;
import com.cafe.model.Login;
import com.cafe.security.exceptions.FailedToLoginException;
import com.cafe.security.service.JwtService;
import com.cafe.security.service.LoginService;
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
    private  LoginService loginService;
    @Autowired
    private  JwtService jwtService;

    @PostMapping()
    public UserDto login(@RequestBody Login credentials, HttpServletResponse response) {
        UserDto userDto = loginService.login(credentials);
        if (userDto != null) {
            String token = jwtService.tokenFor(userDto);
            response.setHeader("Token", token);
            return userDto;
        }
        throw new FailedToLoginException(credentials.getLogin());
    }
}
