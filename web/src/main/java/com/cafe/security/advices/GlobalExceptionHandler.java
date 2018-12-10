package com.cafe.security.advices;

import com.cafe.security.exceptions.FailedToLoginException;
import com.cafe.security.exceptions.ProfileNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.security.SignatureException;

import static org.springframework.http.HttpStatus.*;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String NO_USER = "noUser";
    public static final String NO_USER_WITH_SUCH_DATA = "Нет пользователя с такими данными ";

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ProfileNotFoundException.class)
    public String profileNotFound() {
        return NO_USER;
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(FailedToLoginException.class)
    public String failedToLogin() {
        return NO_USER_WITH_SUCH_DATA;
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(SignatureException.class)
    public String failedToVerify() {
        return NO_USER_WITH_SUCH_DATA;
    }
}
