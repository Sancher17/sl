package com.cafe.controllers.login;

import com.cafe.security.auth.LoginCredentials;
import com.cafe.security.domain.MinimalProfile;
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
    public MinimalProfile login(@RequestBody LoginCredentials credentials, HttpServletResponse response) {
        System.out.println("login controller");
        MinimalProfile minimalProf = loginService
                .login(credentials)//получили MinimalProfile
                .map(minimalProfile -> {
                        String token = jwtService.tokenFor(minimalProfile);
                        response.setHeader("Token", token);
                        log.info("credentials name: " + credentials.getUsername() + " // credentials pass: " + credentials.getPassword());
                        log.info("token " + token);
                        minimalProfile.setToken(token);
                    return minimalProfile;
                })
                .orElseThrow(() -> new FailedToLoginException(credentials.getUsername()));

        return minimalProf;
    }
}
