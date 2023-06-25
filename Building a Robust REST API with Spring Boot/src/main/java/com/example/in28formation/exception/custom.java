package com.example.in28formation.exception;

import com.example.in28formation.User.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class custom extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException
            (Exception ex, WebRequest request){
        ExceptionResounse exceptionResounse =
                new ExceptionResounse(new Date(),ex.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity(exceptionResounse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException
            (UserNotFoundException ex, WebRequest request){
        ExceptionResounse exceptionResounse =
                new ExceptionResounse(new Date(),ex.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity(exceptionResounse, HttpStatus.NOT_FOUND);

    }
}
