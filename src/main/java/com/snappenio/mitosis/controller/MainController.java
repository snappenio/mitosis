package com.snappenio.mitosis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    @RequestMapping("/")
    public String index() {
        return "Welcome to snappen.io!";
    }

}
