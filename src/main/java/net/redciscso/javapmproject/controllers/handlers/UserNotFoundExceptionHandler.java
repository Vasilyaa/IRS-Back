package net.redciscso.javapmproject.controllers.handlers;

import net.redciscso.javapmproject.exception.RestApiError;
import net.redciscso.javapmproject.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserNotFoundExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    //перехватывает исключения типа 404
    public ResponseEntity<RestApiError> userNotFoundHandler(UserNotFoundException ex) {
        return new ResponseEntity<>(new RestApiError(ex.getMessage())
                , HttpStatus.NOT_FOUND);
    }

}

