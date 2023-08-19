package com.epam.demo.exceptionhandler;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.epam.demo.exceptions.BatchException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentsNotValid(MethodArgumentNotValidException e, WebRequest request) {
        List<String> errors = e.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
        return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.toString(), errors.toString(), request.getDescription(false));
    }
    @ExceptionHandler(BatchException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse ProjectException(BatchException e, WebRequest request) {
        return new ExceptionResponse(new Date().toString(), HttpStatus.OK.toString(), e.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleRuntimeException(RuntimeException e, WebRequest request) {
        return new ExceptionResponse(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), request.getDescription(false));
    }
}
