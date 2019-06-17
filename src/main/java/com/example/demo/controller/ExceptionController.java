package com.example.demo.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
   @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map userNameNotUnique(DataIntegrityViolationException exception){
       StringBuilder stringBuilder=new StringBuilder();
       stringBuilder.append("用户已存在");
       return Map.of("message",stringBuilder.toString());
   }
}
