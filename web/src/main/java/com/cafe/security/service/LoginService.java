package com.cafe.security.service;

import com.cafe.security.auth.LoginCredentials;
import com.cafe.security.domain.MinimalProfile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginService {

    private static final Logger log = Logger.getLogger(LoginService.class);

    @Autowired
    private ProfileService profileService;

    public LoginService() {
        log.info("bean created");
    }

    public Optional<MinimalProfile> login(LoginCredentials credentials) {
        System.out.println();
        return profileService.get(credentials.getUsername())
                /** Обратите внимание, что в реальных приложениях вы никогда не захотите это делать.
                 * Вы должны сравнить хешированную версию пароля, предоставленного пользователем,
                 * с хешированной версией пароля, хранящейся в базе данных.**/
                .filter(profile -> profile.getLogin().getPassword().equals(credentials.getPassword()))
                .map(profile -> new MinimalProfile(profile));
    }


}
