package com.dzg.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "I`m trying to learn authenticating a user with LDAP";
    }
}
