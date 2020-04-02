package com.yiyuclub.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControlelr {

    @GetMapping("admin")
    public void admin(){
        System.out.println("admin");
    }

    @GetMapping("user")
    public void user(){
        System.out.println("user");
    }

    @GetMapping("visitor")
    public void visitor(){
        System.out.println("visitor");
    }
}
