package com.gg.duonduo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello");
        return "Hello Test Page";
    }
}