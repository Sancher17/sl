package com.cafe.security.exceptions;

public class FailedToLoginException extends RuntimeException {

    public static final String CAN_NOT_LOGIN_WITH_SUCH_NAME = "Не удается залогироваться с данным именем: ";

    public FailedToLoginException(String username) {
        super(CAN_NOT_LOGIN_WITH_SUCH_NAME + username);
    }
}