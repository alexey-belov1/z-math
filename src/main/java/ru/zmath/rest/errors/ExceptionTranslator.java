package ru.zmath.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionTranslator {       /*extends ResponseEntityExceptionHandler */

    @ExceptionHandler(InsufficientAuthenticationException.class)
    protected ResponseEntity<AwesomeException> handleThereIsNoSuchUserException(RuntimeException ex) {
        return new ResponseEntity<>(new AwesomeException("Логин или пароль введены неверно"), HttpStatus.FORBIDDEN);
    }
}
