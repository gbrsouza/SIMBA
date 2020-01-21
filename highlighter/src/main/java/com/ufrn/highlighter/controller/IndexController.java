package com.ufrn.highlighter.controller;

import com.ufrn.highlighter.model.ApplicationUser;
import com.ufrn.highlighter.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final ApplicationUserService applicationUserService;

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
