package com.hottabych04.app.http.handler;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.BindException;


@ControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler({BindException.class,
            HttpMessageNotReadableException.class,
            PropertyValueException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail invalidBody(){
        return ProblemDetail
                .forStatus(HttpStatus.BAD_REQUEST);
    }

}
