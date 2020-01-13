package com.ufrn.highlighter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    @GetMapping("/login")
    public String login (){
        return "login";
    }

    @GetMapping("/account")
    public String account (){
        return "account";
    }

    @GetMapping("/")
    public String index (){
        return "welcome";
    }

}
