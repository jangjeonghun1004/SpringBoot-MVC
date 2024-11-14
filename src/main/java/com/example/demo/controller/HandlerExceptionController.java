package com.example.demo.controller;

import com.example.demo.exception.HandlerExceptionCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("exception")
public class HandlerExceptionController {

    @GetMapping("/custom")
    public void handlerException() {
        throw new HandlerExceptionCustom();
    }

    @GetMapping("/default")
    public void handlerExceptionDefault() {
        throw new RuntimeException("throw error");
    }

}
