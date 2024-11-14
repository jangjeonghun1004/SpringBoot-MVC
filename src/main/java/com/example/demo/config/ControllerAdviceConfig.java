package com.example.demo.config;

import com.example.demo.exception.HandlerExceptionCustom;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceConfig {

    @ExceptionHandler(HandlerExceptionCustom.class)
    public String handlerExceptionCustom(HandlerExceptionCustom e) {
        return "exception/handlerExceptionCustom";
    }

    @ExceptionHandler
    public String handlerExceptionDefault(Exception e) {
        return "error";
    }
}
