package com.example.OAuth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String home() {
        return "Welcome to Home!";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello Secured!";
    }
}
