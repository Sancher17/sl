package com.cafe.security.exceptions;

import static java.lang.String.format;

public class ProfileNotFoundException extends RuntimeException {

    public ProfileNotFoundException(String username) {
        super(format("Пользователя с именем %s не существует", username));
    }
}
